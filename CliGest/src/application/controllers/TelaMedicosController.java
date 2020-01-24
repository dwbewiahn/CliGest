package application.controllers;

import application.models.Especialidade;
import application.models.Medico;
import application.models.DAO.EspecialidadesDAO;
import application.models.DAO.MedicosDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TelaMedicosController {

	@FXML
	private TextField nome, morada, telefone, codigoPostal, email;

	@FXML
	private ListView<Medico> medicos;

	@FXML
	private ComboBox<String> sexo;

	@FXML
	private VBox formulario, especialidadeBox;

	@FXML
	private ComboBox<Especialidade> especialidade;

	@FXML
	private ListView<Especialidade> listaEspecialidades;
	
	private final int ID_ADD_MEDICO = 99999;

	public TelaMedicosController() {
	}

	@FXML
	private void initialize() {
		atualizarLista();
		medicos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, medicoSelecionado) -> {
			if (medicoSelecionado == null)
				return;
			if (medicoSelecionado.getId() != ID_ADD_MEDICO) {
				preencherCampos(medicoSelecionado);
			} else {
				limparCampos();
			}

		});
		sexo.getItems().addAll("Masculino", "Feminino", "Outro");
		especialidade.getItems().addAll(EspecialidadesDAO.getEspecialidades());
		especialidade.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal == null)
				return;
			if (listaEspecialidades.getItems().isEmpty()) {
				listaEspecialidades.getItems().add(newVal);
				return;
			}
			boolean duplicado = false;
			Especialidade especialidadeParaDeletar = null;
			for (Especialidade especialidade : listaEspecialidades.getItems()) {
				if (newVal.getId() == especialidade.getId()) {
					duplicado = true;
					especialidadeParaDeletar = especialidade;
					break;
				}
			}
			if (duplicado) {
				listaEspecialidades.getItems().remove(especialidadeParaDeletar);
			} else {
				listaEspecialidades.getItems().add(newVal);
			}
			
		});
	}

	private void limparCampos() {
		nome.clear();
		especialidade.setValue(null);
		morada.clear();
		telefone.clear();
		codigoPostal.clear();
		email.clear();
		sexo.setValue(null);
		listaEspecialidades.getItems().clear();
	}

	@FXML
	private void guardar() {
		Medico medico = medicos.getSelectionModel().getSelectedItem();
		atualizarMedico(medico);
		System.out.println("ID VELHO: " + medico.getId());
		if (medico.getId() == ID_ADD_MEDICO) {
			MedicosDAO.criarNovoMedico(medico);
			medico = MedicosDAO.getMedico(medico.getEmail());
		}else {
			MedicosDAO.atualizarMedico(medico);
		}
		System.out.println("ID NOVO: " + medico.getId());
		System.out.println("EMAIL: " + medico.getEmail() + " ID: " + medico.getId());
		registrarEspecialidadesDistintas(medico);
		atualizarLista();
		limparCampos();
	}

	
	private void registrarEspecialidadesDistintas(Medico medico) {
		ObservableList<Especialidade> especialidadesDaLista = listaEspecialidades.getItems();
		ObservableList<Especialidade> especialidadesDoMedico = EspecialidadesDAO.getEspecialidades(medico.getId());
		System.out.println(especialidadesDoMedico);
		if (especialidadesDoMedico.size() < 1) {
			especialidadesDaLista.forEach(especialidade -> {
				System.out.println("Medico nao tem especialidade");
				EspecialidadesDAO.registrarEspecialidade(medico.getId(), especialidade.getId());
			});
		} else {
			for (Especialidade especialidadeDaLista : especialidadesDaLista) {
				boolean encontrado = false;
				for (Especialidade especialidadeDoMedico : especialidadesDoMedico) {
					if (especialidadeDoMedico.getId() == especialidadeDaLista.getId()) {
						encontrado = true;
						break;
					}
				}
				if (!encontrado) {
					EspecialidadesDAO.registrarEspecialidade(medico.getId(), especialidadeDaLista.getId());
				}
			}

			for (Especialidade especialidadeDoMedico : especialidadesDoMedico) {
				boolean encontrado = false;
				for (Especialidade especilidadaDaLista : especialidadesDaLista) {
					if (especialidadeDoMedico.getId() == especilidadaDaLista.getId()) {
						encontrado = true;
						break;
					}
				}
				if (!encontrado) {
					EspecialidadesDAO.removerEspecialidade(medico.getId(), especialidadeDoMedico.getId());
				}
			}

		}
	}

	private void atualizarLista() {
		medicos.getItems().clear();
		Medico novoMedico = new Medico(ID_ADD_MEDICO, "+ Criar Novo Medico", 0, null, null, null, null, null, null);
		medicos.getItems().add(novoMedico);
		medicos.getItems().addAll(MedicosDAO.getMedicos());
		medicos.refresh();

	}

	private void atualizarMedico(Medico medico) {
		medico.setNome(nome.getText());
		medico.setMorada(morada.getText());
		medico.setTelefone(Integer.parseInt(telefone.getText()));
		medico.setCodigoPostal(codigoPostal.getText());
		medico.setEmail(email.getText());
		medico.setSexo(sexo.getValue());
		medico.setEspecialidadades(listaEspecialidades.getItems());
	}

	private void preencherCampos(Medico medico) {
		nome.setText(medico.toString());
		telefone.setText(String.valueOf(medico.getTelefone()));
		morada.setText(medico.getMorada());
		codigoPostal.setText(medico.getCodigoPostal());
		email.setText(medico.getEmail());
		sexo.setValue(medico.getSexo());
		listaEspecialidades.setItems(medico.getEspecialidades());
	}
}

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

/**
 * Tela com a lista de todos medicos da base de dados e suas informações. Existe a possibilidade de criar novos medicos.
 * @author dwbew
 *
 */
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
	
	/**
	 * ID do item de adição de novo médico
	 */
	private final int ID_ADD_MEDICO = 99999;

	public TelaMedicosController() {
	}

	
	/**
	 * Define items da lista como todos os medicos da base de dados.
	 * Define funcionalidade de preencher os campos com os dados do medico selecionado. Caso o medico selecionado for o item de adicionar um novo,
	 * limpa todos os campos.
	 * Define items da combobox sexo com os dados da base de dados.
	 * Define os items da combobox de especialidades como os dados da base de dados.
	 * Define a funcionalidade de adicionar ou remover especialidade ao medico atravez da combobox.
	 */
	@FXML
	private void initialize() {
		atualizarLista();
		medicos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, medicoSelecionado) -> {
			if (medicoSelecionado == null) return;
			
			if (medicoSelecionado.getId() != ID_ADD_MEDICO) {
				preencherCampos(medicoSelecionado);
			} else {
				limparCampos();
			}

		});
		sexo.getItems().addAll("Masculino", "Feminino", "Outro");
		especialidade.getItems().addAll(EspecialidadesDAO.getEspecialidades());
		especialidade.getSelectionModel().selectedItemProperty().addListener( (obs, oldVal, newVal) -> {
			if (newVal == null)	return;
			
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

	/**
	 * limpa todos os campos da tela
	 */
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

	/**
	 * Atualiza informações do medico selecionado. Caso ainda nao exista, cria um novo medico com as informações especificadas.
	 */
	@FXML
	private void guardar() {
		Medico medico = medicos.getSelectionModel().getSelectedItem();
		atualizarMedico(medico);
		
		if (medico.getId() == ID_ADD_MEDICO) {
			MedicosDAO.criarNovoMedico(medico);
			medico = MedicosDAO.getMedico(medico.getEmail());
		}else {
			MedicosDAO.atualizarMedico(medico);
		}

		registrarEspecialidadesDistintas(medico);
		atualizarLista();
		limparCampos();
	}

	/**
	 * Registra especialidades da lista na base de dados apenas se não estiver registrada ainda.
	 * @param medico medico para registrar as especialidade
	 */
	private void registrarEspecialidadesDistintas(Medico medico) {
		ObservableList<Especialidade> especialidadesDaLista = listaEspecialidades.getItems();
		ObservableList<Especialidade> especialidadesDoMedico = EspecialidadesDAO.getEspecialidades(medico.getId());
		System.out.println(especialidadesDoMedico);
		if (especialidadesDoMedico.size() < 1) {
			System.out.println("Medico nao tem especialidade");
			especialidadesDaLista.forEach(especialidade -> {
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

	/**
	 * Define items da lista como todos os medicos da base de dados além do medico de adição.
	 */
	private void atualizarLista() {
		medicos.getItems().clear();
		Medico novoMedico = new Medico(ID_ADD_MEDICO, "+ Criar Novo Medico", 0, null, null, null, null, null, null);
		medicos.getItems().add(novoMedico);
		medicos.getItems().addAll(MedicosDAO.getMedicos());
		medicos.refresh();

	}

	/**
	 * Atualiza a informação do medico especificado no parametro
	 * @param medico medico para atualizar as informações
	 */
	private void atualizarMedico(Medico medico) {
		medico.setNome(nome.getText());
		medico.setMorada(morada.getText());
		medico.setTelefone(Integer.parseInt(telefone.getText()));
		medico.setCodigoPostal(codigoPostal.getText());
		medico.setEmail(email.getText());
		medico.setSexo(sexo.getValue());
		medico.setEspecialidadades(listaEspecialidades.getItems());
	}

	/**
	 * Preenche os campos com a informação do medico especificado
	 * @param medico medico para buscar as informações
	 */
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

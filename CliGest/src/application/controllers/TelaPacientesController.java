package application.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.models.Paciente;
import application.models.Seguradora;
import application.models.DAO.PacientesDAO;
import application.models.DAO.SeguradorasDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TelaPacientesController {

	@FXML
	private TextField nome, contribuinte, morada, telefone, codigoPostal, email;

	@FXML
	private DatePicker dataNascimento;

	@FXML
	private ComboBox<String> sexo;

	@FXML
	private ComboBox<Seguradora> seguradora;

	@FXML
	private ListView<Paciente> pacientes;

	@FXML
	private ListView<Seguradora> listaSeguradoras;

	public TelaPacientesController() {
	}

	@FXML
	private void initialize() {
		atualizarLista();
		pacientes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, pacienteSelecionado) -> {
			if (pacienteSelecionado == null)
				return;

			if (pacienteSelecionado.getId() != 010) {
				preencherCampos(pacienteSelecionado);
			} else {
				limparCampos();
			}

		});
		sexo.getItems().addAll("Masculino", "Feminino", "Outro");

		seguradora.setItems(SeguradorasDAO.getSeguradoras());

		seguradora.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal == null)
				return;
			if (listaSeguradoras.getItems().isEmpty()) {
				listaSeguradoras.getItems().add(newVal);
				return;
			}
			boolean duplicado = false;
			Seguradora seguradoraParaDeletar = null;
			for (Seguradora seguradora : listaSeguradoras.getItems()) {
				if (newVal.getId() == seguradora.getId()) {
					duplicado = true;
					seguradoraParaDeletar = seguradora;
					break;
				}
			}
			if (duplicado) {
				listaSeguradoras.getItems().remove(seguradoraParaDeletar);
			} else {
				listaSeguradoras.getItems().add(newVal);
			}
		});
	}

	private void limparCampos() {
		nome.clear();
		contribuinte.clear();
		morada.clear();
		telefone.clear();
		codigoPostal.clear();
		email.clear();
		listaSeguradoras.getItems().clear();
		dataNascimento.setValue(null);
		sexo.setValue(null);
	}

	@FXML
	private void guardar() {
		Paciente paciente = pacientes.getSelectionModel().getSelectedItem();
		atualizarPaciente(paciente);
		registrarSeguradorasDistintas(paciente);
		if (paciente.getId() == 010) {
			PacientesDAO.criarNovoPaciente(paciente);
		} else {
			PacientesDAO.atualizarPaciente(paciente);
		}
		atualizarLista();
		limparCampos();
	}

	private void registrarSeguradorasDistintas(Paciente paciente) {
		ObservableList<Seguradora> seguradorasDaLista = listaSeguradoras.getItems();
		ObservableList<Seguradora> seguradorasDoPaciente = SeguradorasDAO.getSeguradoras(paciente.getId());

		if (seguradorasDoPaciente.size() < 1) {
			seguradorasDaLista.forEach(seguradora -> {
				System.out.println("Paciente nao tem seguradora");
				SeguradorasDAO.registrarSeguradoras(paciente.getId(), seguradora.getId());
			});
		} else {
			for (Seguradora seguradoraDaLista : seguradorasDaLista) {
				boolean encontrado = false;
				for (Seguradora seguradoraDoPaciente : seguradorasDoPaciente) {
					if (seguradoraDoPaciente.getId() == seguradoraDaLista.getId()) {
						encontrado = true;
						break;
					}
				}
				if (!encontrado) {
					SeguradorasDAO.registrarSeguradoras(paciente.getId(), seguradoraDaLista.getId());
				}
			}

			for (Seguradora seguradoraDoPaciente : seguradorasDoPaciente) {
				boolean encontrado = false;
				for (Seguradora seguradoraDaLista : seguradorasDaLista) {
					if (seguradoraDoPaciente.getId() == seguradoraDaLista.getId()) {
						encontrado = true;
						break;
					}
				}
				if (!encontrado) {
					SeguradorasDAO.removerSeguradora(paciente.getId(), seguradoraDoPaciente.getId());
				}
			}

		}
	}

	private void atualizarLista() {
		pacientes.getSelectionModel().selectFirst();
		pacientes.getItems().clear();
		Paciente novoPaciente = new Paciente(010, 0, "+ Criar Novo Paciente", null, null, null, null, null, null, 0);
		pacientes.getItems().add(novoPaciente);
		pacientes.getItems().addAll(PacientesDAO.getPacientes());

	}

	private void atualizarPaciente(Paciente paciente) {
		paciente.setNome(nome.getText());
		paciente.setContribuinte(Integer.parseInt(contribuinte.getText()));
		paciente.setMorada(morada.getText());
		paciente.setTelefone(Integer.parseInt(telefone.getText()));
		paciente.setCodigoPostal(codigoPostal.getText());
		paciente.setEmail(email.getText());
		paciente.setDataNascimento(
				String.valueOf(dataNascimento.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
		paciente.setSexo(sexo.getValue());
		paciente.setSeguradoras(listaSeguradoras.getItems());
	}

	private void preencherCampos(Paciente paciente) {
		nome.setText(paciente.toString());
		contribuinte.setText(String.valueOf(paciente.getContribuinte()));
		morada.setText(paciente.getMorada());
		telefone.setText(String.valueOf(paciente.getTelefone()));
		codigoPostal.setText(paciente.getCodigoPostal());
		email.setText(paciente.getEmail());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		dataNascimento.setValue(LocalDate.parse(paciente.getDataNascimento(), formatter));
		sexo.setValue(paciente.getSexo());
		listaSeguradoras.setItems(SeguradorasDAO.getSeguradoras(paciente.getId()));
	}
}

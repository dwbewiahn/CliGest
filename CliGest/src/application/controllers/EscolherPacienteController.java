package application.controllers;

import application.models.Agendamento;
import application.models.Especialidade;
import application.models.Medico;
import application.models.Paciente;
import application.models.DAO.AgendamentosDAO;
import application.models.DAO.PacientesDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class EscolherPacienteController {

	@FXML
	private ComboBox<Paciente> pacientes;
	
	@FXML ComboBox<Especialidade> especialidades;
	
	@FXML
	private Label nomeDoMedico;
	
	private Medico medico;
	
	private String dataHora;
	
	@FXML
	private Button agendar;
	
	public EscolherPacienteController(Medico medico, String dataHora) {
		this.medico = medico;
		this.dataHora = dataHora;
	}
	
	@FXML
	private void initialize() {
		pacientes.setItems(PacientesDAO.getPacientes());
		especialidades.setItems(medico.getEspecialidades());
		nomeDoMedico.setText(medico.toString());
	}
	
	@FXML
	private void agendar() {
		AgendamentosDAO.criarAgendamento(new Agendamento(0, dataHora, pacientes.getValue(), medico, especialidades.getValue(), false));
		agendar.getScene().getWindow().hide();
	}
	
	@FXML
	private void cancelar() {
		
	}
}

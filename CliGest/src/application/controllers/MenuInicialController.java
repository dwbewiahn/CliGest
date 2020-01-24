package application.controllers;

import application.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MenuInicialController {

	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab agendamentos, pacientes, medicos;
	
	@FXML
	private void initialize() {
		agendamentos.setContent(ScreenManager.loadFXML("./views/Agenda.fxml", new AgendaController()));
		pacientes.setContent(ScreenManager.loadFXML("./views/TelaPacientes.fxml", new TelaPacientesController()));
		medicos.setContent(ScreenManager.loadFXML("./views/TelaMedicos.fxml", new TelaMedicosController()));
	}
	
	
	@FXML
	private void abrirAgendamentos() {
		
	}
}

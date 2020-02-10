package application.controllers;

import application.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Define as abas existentes no programa e coloca as views especificas para cada uma delas
 * @author dwbew
 *
 */
public class MenuInicialController {

	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab agendamentos, pacientes, medicos;
	
	/**
	 * Define o conteudo de cada uma das abas.
	 */
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

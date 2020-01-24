package application.controllers;

import application.models.Agendamento;
import application.models.DAO.AgendamentosDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AgendamentoController {

	@FXML
	private TextField nomePaciente, nomeMedico, telefonePaciente, especialidadeMedico;
	
	@FXML
	private Button confirmar, cancelar;
	
	private Agendamento agendamento;
	
	private boolean confirmado;
	
	public AgendamentoController(Agendamento agendamento) {
		confirmado = agendamento.isConfirmado();
		this.agendamento = agendamento;
	}
	
	@FXML
	private void initialize() {
		if(confirmado) setBotaoConfirmado();
		nomePaciente.setText(agendamento.getPaciente().toString());
		nomeMedico.setText(agendamento.getMedico().toString());
		telefonePaciente.setText(String.valueOf(agendamento.getPaciente().getTelefone()));
		especialidadeMedico.setText(agendamento.getEspecialidade().toString());
	}
	
	@FXML
	private void confirmarAgendamento() {
		confirmado = !confirmado;
		if(confirmado) {
			setBotaoConfirmado();
		}else {
			confirmar.setText("Confirmar");
			confirmar.setStyle("-fx-background-color: #33bdef;"
					+ " -fx-text-fill: white;");
		}
		
		AgendamentosDAO.setConfirmado(confirmado, agendamento.getId());
	}
	
	@FXML
	private void cancelarAgendamento() {
		AgendamentosDAO.deletarAgendamento(agendamento.getId());
		System.out.println(agendamento.getMedico() + " Agendamento Cancelado para a data " + agendamento.getDataHora());
		cancelar.getScene().getWindow().hide();
	}

	private void setBotaoConfirmado() {
		confirmar.setText("Confirmado");
		confirmar.setStyle("-fx-background-color: green;"
						+ " -fx-text-fill: white;");
	}
}

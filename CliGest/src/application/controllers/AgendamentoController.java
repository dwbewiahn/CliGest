package application.controllers;

import application.models.Agendamento;
import application.models.DAO.AgendamentosDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlador da tela para ver um agendamento já criado
 * @author dwbew
 *
 */
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
	
	/**
	 * Verifica se o agendamento já está confirmado.
	 * Define os textos conforme o agendamento.
	 */
	@FXML
	private void initialize() {
		if(confirmado) setBotaoConfirmado();
		nomePaciente.setText(agendamento.getPaciente().toString());
		nomeMedico.setText(agendamento.getMedico().toString());
		telefonePaciente.setText(String.valueOf(agendamento.getPaciente().getTelefone()));
		especialidadeMedico.setText(agendamento.getEspecialidade().toString());
	}
	
	/**
	 * Define confirmado caso não esteja confirmado ainda. Caso contrario, retira a confirmação. Muda a cor e o texto do botão
	 * conforme o estado
	 */
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
	
	/**
	 * Deleta o agendamento da base de dados e fecha a tela que foi aberta.
	 */
	@FXML
	private void cancelarAgendamento() {
		AgendamentosDAO.deletarAgendamento(agendamento.getId());
		System.out.println(agendamento.getMedico() + " Agendamento Cancelado para a data " + agendamento.getDataHora());
		cancelar.getScene().getWindow().hide();
	}

	/**
	 * Define o botão como confirmado.
	 */
	private void setBotaoConfirmado() {
		confirmar.setText("Confirmado");
		confirmar.setStyle("-fx-background-color: green;"
						+ " -fx-text-fill: white;");
	}
}

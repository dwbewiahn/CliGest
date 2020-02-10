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

/**
 * Controlador para criar um agendamento.
 * @author dwbew
 *
 */
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
	
	/**
	 * Define os items da combobox de pacientes com os dados da base dados.
	 * Define os items da combobox de especialidade com os dados da base de dados.
	 * Define o nome do medico para aparecer na tela como o medico escolhido.
	 */
	@FXML
	private void initialize() {
		pacientes.setItems(PacientesDAO.getPacientes());
		especialidades.setItems(medico.getEspecialidades());
		nomeDoMedico.setText(medico.toString());
	}
	
	/**
	 * Cria um novo agendamento com os items selecionados nas combobox e fecha a tela.
	 */
	@FXML
	private void agendar() {
		AgendamentosDAO.criarAgendamento(new Agendamento(0, dataHora, pacientes.getValue(), medico, especialidades.getValue(), false));
		agendar.getScene().getWindow().hide();
	}
	
	/**
	 * Fecha a tela atual sem salvar nenhuma informação
	 */
	@FXML
	private void cancelar() {
		agendar.getScene().getWindow().hide();
	}
}

package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DBConnector;
import application.models.Agendamento;
import application.models.Especialidade;
import application.models.Medico;
import application.models.Paciente;

/**
 * Classe par buscar toda a informação dos agendamentos na base de dados
 * @author dwbew
 *
 */
public class AgendamentosDAO {

	/**
	 * Recebe se o horario do medico e da hora especificado está confirmado.
	 * @param idMedico id do medico da consulta
	 * @param dataHora data e hora da consulta
	 * @return se o agendamento está confirmado
	 */
	public static boolean isConfirmado(int idMedico, String dataHora) {
		String[] dataHoraArray = dataHora.split(",");
		boolean confirmado = false;
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT confirmado FROM agendamento WHERE Medico_id_medico = ? AND agendamento_data = ? AND agendamento_hora = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setInt(1, idMedico);
			stat.setString(2, dataHoraArray[0]);
			stat.setString(3, dataHoraArray[1]);
			try(ResultSet rs = stat.executeQuery()){
				if(rs.next())
				confirmado = rs.getBoolean(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return confirmado;
	}
	
	/**
	 * Busca o agendamento na base de daods com o id do medico e a hora especificados
	 * @param medicoId id do medico do agendamento
	 * @param dataHora data e hora do agendamento
	 * @return o agendamento com o medico e hora passados nos parametros
	 */
	public static Agendamento getAgendamento(int medicoId, String dataHora) {
		String[] dataHoraArray = dataHora.split(",");
		Connection conn = DBConnector.getConnection();
		Agendamento agendamento = null;
		String sql = "SELECT id_agendamento, agendamento_hora, agendamento_data, Paciente_id_paciente,"
				+ " Medico_id_medico, confirmado, Especialidade_id_especialidade, especialidade"
				+ " FROM agendamento INNER JOIN especialidade ON Especialidade_id_especialidade = id_especialidade"
				+ " WHERE Medico_id_medico = ? AND agendamento_data = ? AND agendamento_hora = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setInt(1, medicoId);
			stat.setString(2, dataHoraArray[0]);
			stat.setString(3, dataHoraArray[1]);
			try(ResultSet rs = stat.executeQuery()){
				if(rs.next()) {
					int id = rs.getInt(1);
					String data = rs.getString(2);
					String hora = rs.getString(3);
					Paciente paciente = PacientesDAO.getPaciente(rs.getInt(4));
					Medico medico = MedicosDAO.getMedico(rs.getInt(5));
					boolean confirmado = rs.getBoolean(6);
					Especialidade especialidade = new Especialidade(rs.getInt(7), rs.getString(8));
					agendamento = new Agendamento(id, data+hora, paciente, medico, especialidade, confirmado);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return agendamento;
	}
	
	/**
	 * Define como o parametro confirmado o agendamento com o id passado nos parametros
	 * @param confirmado estado que deseja o agendamento
	 * @param agendamentoId agendamento que deseja definir o estado
	 */
	public static void setConfirmado(boolean confirmado, int agendamentoId) {
		Connection conn = DBConnector.getConnection();
		String sql = "UPDATE agendamento SET confirmado=? WHERE id_agendamento= ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setBoolean(1, confirmado);
			stat.setInt(2, agendamentoId);
			stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cria um agendamento com as caracteristicas passadas no paramentro
	 * @param agendamento agendamento com as caracteristicas desejadas
	 */
	public static void criarAgendamento(Agendamento agendamento) {
		Connection conn = DBConnector.getConnection();
		String[] dataHoraArray = agendamento.getDataHora().split(",");
		String sql = "INSERT INTO agendamento(agendamento_data, agendamento_hora, Paciente_id_paciente, Medico_id_medico, Especialidade_id_especialidade)"
					+ " VALUES(?,?,?,?,?)";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, dataHoraArray[0]);
			stat.setString(2, dataHoraArray[1]);
			stat.setInt(3, agendamento.getPaciente().getId());
			stat.setInt(4, agendamento.getMedico().getId());
			stat.setInt(5, agendamento.getEspecialidade().getId());
			stat.executeUpdate();
			System.out.println("Agendamento criado com sucesso...");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deleta o agendamento com o id passado no parametro.
	 * @param idAgendamento id do agendameto que deseja deletar
	 */
	public static void deletarAgendamento(int idAgendamento) {
		Connection conn = DBConnector.getConnection();
		String sql = "DELETE FROM agendamento WHERE id_agendamento = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setInt(1, idAgendamento);
			stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
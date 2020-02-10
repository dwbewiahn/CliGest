package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DBConnector;
import application.models.Seguradora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe que busca e atualiza informações sobre as seguradoras na base de dados
 * @author dwbew
 *
 */
public class SeguradorasDAO {

	/**
	 * Busca todas as seguradoras registradas na base de dados.
	 * @return Lista com todas as seguradoras da base de dados.
	 */
	public static ObservableList<Seguradora> getSeguradoras() {
		ObservableList<Seguradora> seguradoras = FXCollections.observableArrayList();
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT * FROM seguradora";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				seguradoras.add(new Seguradora(id, nome));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return seguradoras;
	}
	
	/**
	 * Busca todas as seguradoras do paciente com o id especificado nos paramentros
	 * @param idPaciente id do paciente que deseja buscar as seguradoras
	 * @return Lista com todas as seguradoras do paciente especificado
	 */
	public static ObservableList<Seguradora> getSeguradoras(int idPaciente) {
		ObservableList<Seguradora> seguradoras = FXCollections.observableArrayList();
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT Seguradora_id_seguradora, nome_seguradora FROM paciente_has_seguradora INNER JOIN seguradora "
				+ "ON Seguradora_id_seguradora = id_seguradora WHERE Paciente_id_paciente = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setInt(1, idPaciente);
			try(ResultSet rs = stat.executeQuery()){
			while(rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				seguradoras.add(new Seguradora(id, nome));
			}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return seguradoras;
	}
	
	/**
	 * Deleta a seguradora especificada da lista de seguradoras do paciente com o id especificado nos parametros.
 	 * @param idPaciente id do paciente que deseja remover a seguradora
	 * @param idSeguradora id da seguradora que deseja remover da lista do paciente.
	 */
	public static void removerSeguradora(int idPaciente,int idSeguradora) {
		Connection conn = DBConnector.getConnection();
		String sql = "DELETE FROM paciente_has_seguradora WHERE Seguradora_id_seguradora = ? AND Paciente_id_paciente = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setInt(1, idSeguradora);
			stat.setInt(2, idPaciente);
			stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Registra uma nova seguradora para o paciennte com id especificado nos parametros.
	 * @param idPaciente id do paciente que deseja registrar a nova seguradora
	 * @param idSeguradora id da seguradora que deseja registrar para o cliente.
	 */
	public static void registrarSeguradoras(int idPaciente, int idSeguradora) {
		Connection conn = DBConnector.getConnection();
		String sql = "INSERT INTO paciente_has_seguradora(Paciente_id_paciente, Seguradora_id_seguradora)"
					+ " VALUES(?,?)";
		System.out.println("Registrando seguradora...");
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setInt(1, idPaciente);
			stat.setInt(2, idSeguradora);
			stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
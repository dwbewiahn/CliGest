package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DBConnector;
import application.models.Especialidade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe que lida com a busca e atualização de informaçoes das especialidades na base de dados
 * @author dwbew
 *
 */
public class EspecialidadesDAO {

	/**
	 * Busca todas as especialidades disponiveis na base de dados
	 * @return todas as especialidades disponiveis na base de dados
	 */
	public static ObservableList<Especialidade> getEspecialidades() {
		ObservableList<Especialidade> especialidades = FXCollections.observableArrayList();
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT * FROM especialidade";
		try (Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)) {
			while (rs.next()) {
				int id = rs.getInt("id_especialidade");
				String nome = rs.getString("especialidade");
				especialidades.add(new Especialidade(id, nome));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return especialidades;
	}

	/**
	 * Busca todas as especialidades do medico com o id especificado nos parametros
	 * @param idMedico id do medico que deseja receber as especialidades
	 * @return Lista com todas especialidades do medico
	 */
	public static ObservableList<Especialidade> getEspecialidades(int idMedico) {
		System.out.println("ID MEDICO: " + idMedico);
		ObservableList<Especialidade> especialidades = FXCollections.observableArrayList();
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT id_especialidade, especialidade FROM medico_has_especialidade"
				+ " INNER JOIN especialidade ON Especialidade_id_especialidade = id_especialidade"
				+ " WHERE Medico_id_medico = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, idMedico);
			try (ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String nome = rs.getString(2);
					especialidades.add(new Especialidade(id, nome));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(especialidades);
		return especialidades;
	}

	/**
	 * Registra uma nova especialidade para o medico com o id passado nos parametros
	 * @param idMedico id do medico para registrar a especialidade
	 * @param idEspecialidade id da especialidade que deseja adicionar ao medico
	 */
	public static void registrarEspecialidade(int idMedico, int idEspecialidade) {
		Connection conn = DBConnector.getConnection();
		String sql = "INSERT INTO medico_has_especialidade(Medico_id_medico, Especialidade_id_especialidade)"
				+ " VALUES(?,?)";
		System.out.println("Registrando seguradora...");
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, idMedico);
			stat.setInt(2, idEspecialidade);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove a especialidade especificada do medico com o id do parametro
	 * @param idMedico id do medico que deseja remover a especialidade
	 * @param idEspecialidade id da especialidade que deseja remover
	 */
	public static void removerEspecialidade(int idMedico, int idEspecialidade) {
		Connection conn = DBConnector.getConnection();
		String sql = "DELETE FROM medico_has_especialidade WHERE Especialidade_id_especialidade = ? AND Medico_id_medico = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, idEspecialidade);
			stat.setInt(2, idMedico);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

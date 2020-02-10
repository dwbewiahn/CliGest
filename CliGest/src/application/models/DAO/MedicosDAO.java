package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DBConnector;
import application.models.Especialidade;
import application.models.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe que lida com a busca e atualizacao de dados relativos aos medicos da base de dados
 * @author dwbew
 *
 */
public class MedicosDAO {

	/**
	 * Busca todos os medicos registrados na base de dados
	 * @return Lista com todos os medicos da base de dados
	 */
	public static ObservableList<Medico> getMedicos() {
		ObservableList<Medico> medicos = FXCollections.observableArrayList();
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT id_medico, nome_medico, telefone, email, morada, cod_postal, sexo, "
				+ "GROUP_CONCAT(id_especialidade), GROUP_CONCAT(especialidade) FROM medico "
				+ "INNER JOIN medico_has_especialidade ON medico_id_medico = id_medico INNER JOIN especialidade ON especialidade_id_especialidade = id_especialidade GROUP BY id_medico";
		try (Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)) {
			while (rs.next()) {
				int id = rs.getInt("id_medico");
				String nome = rs.getString("nome_medico");
				int telefone = rs.getInt("telefone");
				String email = rs.getString("email");
				String morada = rs.getString("morada");
				String sexo = rs.getString("sexo");
				String codigoPostal = rs.getString("cod_postal");
				String[] especialidadesIdString = rs.getString("GROUP_CONCAT(id_especialidade)").split(",");
				String[] especialidadesNomeString = rs.getString("GROUP_CONCAT(especialidade)").split(",");
				ObservableList<Especialidade> especialidades = FXCollections.observableArrayList();
				for (int i = 0; i < especialidadesIdString.length; i++) {
					especialidades.add(new Especialidade(Integer.parseInt(especialidadesIdString[i]),
							especialidadesNomeString[i]));
				}
				medicos.add(new Medico(id, nome, telefone, sexo, morada, codigoPostal, email, "2000-01-01",
						especialidades));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medicos;
	}

	/**
	 * Busca todos os horarios marcados do medico com id especificado nos parametros
	 * @param id id do medico que deseja receber os horarios marcados
	 * @return Lista com todos os horarios marcados do medico
	 */
	public static ObservableList<String> getHorariosMarcados(int id) {
		ObservableList<String> horariosMarcados = FXCollections.observableArrayList();
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT * FROM agendamento WHERE Medico_id_medico = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, id);
			try (ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
					String data = rs.getString("agendamento_data");
					String hora = rs.getString("agendamento_hora");
					horariosMarcados.add(data + "," + hora);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horariosMarcados;
	}


	/**
	 * Recebe o medico com o id especificado nos parametros
	 * @param idMedico id do medico que deseja buscar na base de dados
	 * @return Medico com o id especificado nos parametros
	 */
	public static Medico getMedico(int idMedico) {
		Medico medico = null;
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT id_medico, nome_medico, sexo, telefone, email, morada, cod_postal, "
				+ "GROUP_CONCAT(id_especialidade), GROUP_CONCAT(especialidade) FROM medico "
				+ "INNER JOIN medico_has_especialidade ON medico_id_medico = id_medico INNER JOIN especialidade"
				+ " ON especialidade_id_especialidade = id_especialidade WHERE id_medico = ? GROUP BY id_medico";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, idMedico);
			try (ResultSet rs = stat.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("id_medico");
					String nome = rs.getString("nome_medico");
					String sexo = rs.getString("sexo");
					int telefone = rs.getInt("telefone");
					String email = rs.getString("email");
					String morada = rs.getString("morada");
					String codigoPostal = rs.getString("cod_postal");
					String[] especialidadesIdString = rs.getString("GROUP_CONCAT(id_especialidade)").split(",");
					String[] especialidadesNomeString = rs.getString("GROUP_CONCAT(especialidade)").split(",");
					ObservableList<Especialidade> especialidades = FXCollections.observableArrayList();
					for (int i = 0; i < especialidadesIdString.length; i++) {
						especialidades.add(new Especialidade(Integer.parseInt(especialidadesIdString[i]),
								especialidadesNomeString[i]));
					}
					medico = new Medico(id, nome, telefone, sexo, morada, codigoPostal, email, "2000-01-01",
							especialidades);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medico;
	}
	
	/**
	 * Busca o medico especificado nos parametros na base de dados
	 * @param medicoA medico que deseja buscar na base de dados
	 * @return medico com as informações passadas no parametro
	 */
	public static Medico getMedico(Medico medicoA) {
		Medico medico = null;
		System.out.println(medicoA.getTelefone());
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT id_medico, nome_medico, sexo, telefone, email, morada, cod_postal,"
				+ " GROUP_CONCAT(id_especialidade), GROUP_CONCAT(especialidade)"
				+ " FROM medico INNER JOIN medico_has_especialidade ON medico_id_medico = id_medico INNER JOIN especialidade"
				+ " ON especialidade_id_especialidade = id_especialidade WHERE telefone = ? GROUP BY id_medico";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, medicoA.getTelefone());
			try (ResultSet rs = stat.executeQuery()) {
				if (rs.next()) {
					System.out.println("Medico encontrado");
					int id = rs.getInt("id_medico");
					String nome = rs.getString("nome_medico");
					String sexo = rs.getString("sexo");
					int telefone = rs.getInt("telefone");
					String email = rs.getString("email");
					String morada = rs.getString("morada");
					String codigoPostal = rs.getString("cod_postal");
					String[] especialidadesIdString = rs.getString("GROUP_CONCAT(id_especialidade)").split(",");
					String[] especialidadesNomeString = rs.getString("GROUP_CONCAT(especialidade)").split(",");
					ObservableList<Especialidade> especialidades = FXCollections.observableArrayList();
					for (int i = 0; i < especialidadesIdString.length; i++) {
						especialidades.add(new Especialidade(Integer.parseInt(especialidadesIdString[i]),
								especialidadesNomeString[i]));
					}
					medico = new Medico(id, nome, telefone, sexo, morada, codigoPostal, email, "2000-01-01",
							especialidades);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Medico pego com sucesso");
		return medico;
	}
	
	/**
	 * Busca o medico registrado na base de dados com o email passado no parametro
 	 * @param emailMedico email do medico que deseja buscar
	 * @return Medico com o email especificado
	 */
	public static Medico getMedico(String emailMedico) {
		Medico medico = null;
		System.out.println(emailMedico);
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT id_medico, nome_medico, sexo, telefone, email, morada, cod_postal "
				+ "FROM medico WHERE email = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, emailMedico);
			try (ResultSet rs = stat.executeQuery()) {
				if (rs.next()) {
					System.out.println("Medico encontrado");
					int id = rs.getInt("id_medico");
					String nome = rs.getString("nome_medico");
					String sexo = rs.getString("sexo");
					int telefone = rs.getInt("telefone");
					String email = rs.getString("email");
					String morada = rs.getString("morada");
					String codigoPostal = rs.getString("cod_postal");
					medico = new Medico(id, nome, telefone, sexo, morada, codigoPostal, email, "2000-01-01", FXCollections.observableArrayList());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Medico pego com sucesso");
		return medico;
	}

	/**
	 * Cria um novo medico com as informacoes do parametro
	 * @param medico medico que deseja registrar na base de dados
	 */
	public static void criarNovoMedico(Medico medico) {
		Connection conn = DBConnector.getConnection();
		String sql = "INSERT INTO medico(nome_medico, sexo, telefone, email, morada, cod_postal)" + " VALUES(?,?,?,?,?,?)";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, medico.toString());
			stat.setString(2, medico.getSexo());
			stat.setInt(3, medico.getTelefone());
			stat.setString(4, medico.getEmail());
			stat.setString(5, medico.getMorada());
			stat.setString(6, medico.getCodigoPostal());
			stat.executeUpdate();
			System.out.println("Medico criado com sucesso...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Atualiza informacoes do medico especificado nos parametros
	 * @param medico informacoes do medico que deseja atualizar
	 */
	public static void atualizarMedico(Medico medico) {
		Connection conn = DBConnector.getConnection();
		String sql = "UPDATE medico SET nome_medico =?, sexo=?, telefone=?, email=?, morada=?, cod_postal=? WHERE id_medico= ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, medico.toString());
			stat.setString(2, medico.getSexo());
			stat.setInt(3, medico.getTelefone());
			stat.setString(4, medico.getEmail());
			stat.setString(5, medico.getMorada());
			stat.setString(6, medico.getCodigoPostal());
			stat.setInt(7, medico.getId());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

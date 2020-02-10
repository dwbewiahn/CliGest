package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DBConnector;
import application.models.Paciente;
import application.models.Seguradora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe que lida com a busca e atualizacao dos pacientes da base de dados
 * @author dwbew
 *
 */
public class PacientesDAO {

	/**
	 * Busca todos os pacientes registrados na base de dados
	 * @return lista com todos os pacientes registrados na base de dados
	 */
	public static ObservableList<Paciente> getPacientes() {
		ObservableList<Paciente> pacientes = FXCollections.observableArrayList();
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT id_paciente, nome, telefone, sexo, email, morada, cod_postal, "
				+ "nascimento, contribuinte,  GROUP_CONCAT(id_seguradora), GROUP_CONCAT(nome_seguradora) FROM paciente INNER JOIN paciente_has_seguradora ON paciente_id_paciente = Seguradora_id_seguradora "
				+ "INNER JOIN seguradora ON Seguradora_id_seguradora = id_seguradora GROUP BY id_paciente";
		try (Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)) {
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				int telefone = rs.getInt(3);
				String sexo = rs.getString(4);
				String email = rs.getString(5);
				String morada = rs.getString(6);
				String codigoPostal = rs.getString(7);
				String nascimento = rs.getString(8);
				int contribuinte = rs.getInt(9);
				String[] idSeguradoras = rs.getString(10).split(",");
				String[] nomeSeguradoras = rs.getString(11).split(",");
				ObservableList<Seguradora> seguradoras = FXCollections.observableArrayList();
				for(int i = 0; i< idSeguradoras.length; i++) {
					seguradoras.add(new Seguradora(Integer.parseInt(idSeguradoras[i]), nomeSeguradoras[i]));
				}
				
				pacientes.add(new Paciente(id, telefone, nome, sexo, morada, codigoPostal, email, nascimento,
						seguradoras, contribuinte));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacientes;
	}

	/**
	 * Busca o paciente com o id especificado no paramentro
	 * @param idPaciente id do paciente que deseja buscar na base de dados
	 * @return Paciente com o id especificado
	 */
	public static Paciente getPaciente(int idPaciente) {
		Connection conn = DBConnector.getConnection();
		Paciente paciente = null;
		String sql = "SELECT id_paciente, nome, telefone, sexo, email, morada, cod_postal, "
				+ "nascimento, contribuinte, GROUP_CONCAT(id_seguradora), GROUP_CONCAT(nome_seguradora) FROM paciente INNER JOIN paciente_has_seguradora ON paciente_id_paciente = Seguradora_id_seguradora "
				+ "INNER JOIN seguradora ON Seguradora_id_seguradora = id_seguradora WHERE id_paciente = ? GROUP BY id_paciente";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, idPaciente);
			try (ResultSet rs = stat.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt(1);
					String nome = rs.getString(2);
					int telefone = rs.getInt(3);
					String sexo = rs.getString(4);
					String email = rs.getString(5);
					String morada = rs.getString(6);
					String codigoPostal = rs.getString(7);
					String nascimento = rs.getString(8);
					int contribuinte = rs.getInt(9);
					String[] idSeguradoras = rs.getString(10).split(",");
					String[] nomeSeguradoras = rs.getString(11).split(",");
					ObservableList<Seguradora> seguradoras = FXCollections.observableArrayList();
					for(int i = 0; i< idSeguradoras.length; i++) {
						seguradoras.add(new Seguradora(Integer.parseInt(idSeguradoras[i]), nomeSeguradoras[i]));
					}
					
					paciente = new Paciente(id, telefone, nome, sexo, morada, codigoPostal, email, nascimento,
							seguradoras, contribuinte);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paciente;
	}

	/**
	 * Cria um novo paciente com as informações passadas nos parametros
	 * @param paciente Informação do paciente que deseja criar
	 */
	public static void criarNovoPaciente(Paciente paciente) {
			Connection conn = DBConnector.getConnection();
			String sql = "INSERT INTO paciente(nome, contribuinte, morada, cod_postal, nascimento, sexo, telefone, email)"
						+ " VALUES(?,?,?,?,?,?,?,?)";
			try(PreparedStatement stat = conn.prepareStatement(sql)){
				stat.setString(1, paciente.toString());
				stat.setInt(2, paciente.getContribuinte());
				stat.setString(3, paciente.getMorada());
				stat.setString(4, paciente.getCodigoPostal());
				stat.setString(5, paciente.getDataNascimento());
				stat.setString(6, paciente.getSexo());
				stat.setInt(7, paciente.getTelefone());
				stat.setString(8, paciente.getEmail());
				stat.executeUpdate();
				System.out.println("Paciente criado com sucesso...");
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Busca um paciente na base de dados com o email que está especificado nos parametros
	 * @param emailPaciente email do paciente que deseja buscar na base de dados
	 * @return Paciente com o mesmo email passado no parametro
	 */
	public static Paciente getPaciente(String emailPaciente) {
		Paciente paciente = null;
		System.out.println(emailPaciente);
		Connection conn = DBConnector.getConnection();
		String sql = "SELECT * FROM paciente WHERE email = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, emailPaciente);
			try (ResultSet rs = stat.executeQuery()) {
				if (rs.next()) {
					System.out.println("Paciente encontrado");
					int id = rs.getInt("id_paciente");
					String nome = rs.getString("nome");
					String sexo = rs.getString("sexo");
					int telefone = rs.getInt("telefone");
					String email = rs.getString("email");
					String morada = rs.getString("morada");
					String codigoPostal = rs.getString("cod_postal");
					String dataNascimento = rs.getString("nascimento");
					int contribuinte = rs.getInt("contribuinte");
					paciente = new Paciente(id, telefone, nome, sexo, morada, codigoPostal, email, dataNascimento, FXCollections.observableArrayList(), contribuinte);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paciente;
	}
	

	/**
	 * Atualiza o paciente com toda a informação passada nos parametros.
	 * @param paciente paciente com a informação já atualizada para registrar na base de dados.
	 */
	public static void atualizarPaciente(Paciente paciente) {
		Connection conn = DBConnector.getConnection();
		String sql = "UPDATE paciente SET nome=?, contribuinte=?, morada=?, cod_postal=?,"
				+ " nascimento=?, sexo=?, telefone=?, email=? WHERE id_paciente= ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, paciente.toString());
			stat.setInt(2, paciente.getContribuinte());
			stat.setString(3, paciente.getMorada());
			stat.setString(4, paciente.getCodigoPostal());
			stat.setString(5, paciente.getDataNascimento());
			stat.setString(6, paciente.getSexo());
			stat.setInt(7, paciente.getTelefone());
			stat.setString(8, paciente.getEmail());
			stat.setInt(9, paciente.getId());
			stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	

}

package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que faz a conexão com a base de dados
 * @author dwbew
 *
 */
public class DBConnector {
	private static final String URL = "jdbc:mysql://remotemysql.com:3306/XLuUja9EDK";

	private static Connection con;
	private static final String USER = "XLuUja9EDK";
	private static final String PASS = "3FoXwZHdhZ";

	private DBConnector() {
	}


	/**
	 * Pega o conector da base de dados. caso não esteja iniciado, faz a inicialização
	 * @return Conector da base de dados
	 */
	public static Connection getConnection() {
		try {
			if (con == null)
				con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
	}
		return con;
	}
}

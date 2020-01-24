package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Responsável por fazer conexão com banco de dados
 * 
 * @author gilsonalves
 *
 */
public class SingleConnection {
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "12345678";
	private static String user = "postgres";
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingleConnection() {
		conectar();
	}

	public static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);

			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com a base de dados!");
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}

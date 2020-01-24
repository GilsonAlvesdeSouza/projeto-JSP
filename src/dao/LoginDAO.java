package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;

public class LoginDAO {

	private Connection connection;

	public LoginDAO() {
		connection = SingleConnection.getConnection();
	}

	public Boolean validarLogin(String login, String senha) throws SQLException {
		String sql = "select * from usuario where login = '" + login + "' and senha = '" + senha + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			System.out.println("Possui usuario");
			return true;
		} else {
			System.out.println("Nenhum usuario encontrado");
			return false;
		}

	}

}

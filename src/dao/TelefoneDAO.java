package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.TelefoneBean;
import connection.SingleConnection;

public class TelefoneDAO {
	private Connection connection;

	public TelefoneDAO() {
		connection = SingleConnection.getConnection();
	}

	/**
	 * Método para salvar o Telefone
	 * 
	 * @param usuario
	 * @throws SQLException
	 */
	public void salvar(TelefoneBean telefone) throws SQLException {
		String sql = "insert into telefone(numero, tipo, usuario) values (?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getIdUser());
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Método para listar o Telefone
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<TelefoneBean> listar(Long id) throws SQLException {
		List<TelefoneBean> telefones = new ArrayList<>();
		String sql = "select * from telefone where usuario ='" + id + "'";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				TelefoneBean telefone = new TelefoneBean();
				telefone.setId(resultado.getLong("id"));
				telefone.setNumero(resultado.getString("numero"));
				telefone.setTipo(resultado.getString("tipo"));
				telefone.setIdUser(resultado.getLong("usuario"));
				telefones.add(telefone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return telefones;
	}

	/**
	 * Método que consulta o Telefone pelo ID
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public TelefoneBean consultar(Long id) throws SQLException {
		String sql = "Select * from telefone where id= '" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {
			TelefoneBean telefone = new TelefoneBean();
			telefone.setId(resultado.getLong("id"));
			telefone.setNumero(resultado.getString("numero"));
			telefone.setTipo(resultado.getString("tipo"));
			telefone.setIdUser(resultado.getLong("usuario"));
			return telefone;
		}
		return null;
	}

	/**
	 * Método que edita o Telefone
	 * 
	 * @param usuario
	 * @throws SQLException
	 */
	public void atualizar(TelefoneBean telefone) throws SQLException {
		String sql = "update telefone set numero = ?, tipo = ?, usuario = ? where id = " + telefone.getId();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getIdUser());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Método que deleta o Usuário pelo ID
	 * 
	 * @param id
	 * @return
	 */
	public Boolean delete(Long id) {
		String sql = "delete from telefone where id = '" + id + "'";
		Boolean retorno = null;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
			retorno = true;
		} catch (SQLException e) {
			try {
				connection.rollback();
				retorno = false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			retorno = false;
		}
		return retorno;
	}

}

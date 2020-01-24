package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ProdutoBean;
import connection.SingleConnection;

public class ProdutoDAO {
	private Connection connection;

	public ProdutoDAO() {

		connection = SingleConnection.getConnection();
	}

	/**
	 * Método para salvar o Produto
	 * 
	 * @param produto
	 * @throws SQLException
	 */
	public void salvar(ProdutoBean produto) throws SQLException {
		String sql = "insert into produto(descricao, quantidade, preco) values (?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getDescricao());
			statement.setShort(2, produto.getQuantidade());
			statement.setBigDecimal(3, produto.getPreco());
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
	 * Método para listar o produto
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<ProdutoBean> listar() throws SQLException {
		List<ProdutoBean> produtos = new ArrayList<>();
		String sql = "select * from produto order by descricao";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				ProdutoBean produto = new ProdutoBean();
				produto.setId(resultado.getLong("id"));
				produto.setDescricao(resultado.getString("descricao"));
				produto.setPreco(resultado.getBigDecimal("preco"));
				produto.setQuantidade(resultado.getShort("quantidade"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return produtos;
	}

	/**
	 * Método que busca um produto
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ProdutoBean consultar(Long id) throws SQLException {
		String sql = "Select * from produto where id= '" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {
			ProdutoBean produto = new ProdutoBean();
			produto.setId(resultado.getLong("id"));
			produto.setDescricao(resultado.getString("descricao"));
			produto.setPreco(resultado.getBigDecimal("preco"));
			produto.setQuantidade(resultado.getShort("quantidade"));
			return produto;
		}
		return null;
	}

	/**
	 * Método que Altera um produto na base de dados
	 * 
	 * @param usuario
	 * @throws SQLException
	 */
	public void atualizar(ProdutoBean produto) throws SQLException {
		String sql = "update produto set descricao = ?, preco = ?, quantidade = ? where id = " + produto.getId();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getDescricao());
			statement.setBigDecimal(2, produto.getPreco());
			statement.setShort(3, produto.getQuantidade());
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
	 * Método para excluir um usuário do banco de dados
	 * 
	 * @param id
	 * @return
	 */
	public Boolean delete(Long id) {
		String sql = "delete from produto where id = '" + id + "'";
		Boolean retorno = null;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
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

	/**
	 * Método para validar se tem produto com a mesmo descrição
	 * 
	 * @param descricao
	 * @return
	 * @throws SQLException
	 */
	public Boolean validarLogin(String descricao) throws SQLException {
		String sql = "Select count(1) as qtd from produto where descricao= '" + descricao + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {
			return resultado.getInt("qtd") <= 0;
		}
		return false;
	}

}

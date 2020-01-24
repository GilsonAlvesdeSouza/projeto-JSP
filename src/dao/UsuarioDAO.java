package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UsuarioBean;
import connection.SingleConnection;

public class UsuarioDAO {
	private Connection connection;

	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}

	/**
	 * Método para salvar o Usuário
	 * 
	 * @param usuario
	 * @throws SQLException
	 */
	public void salvar(UsuarioBean usuario) throws SQLException {
		String sql = "insert into usuario(login, senha, nome, telefone, cep, rua, numero, bairro, complemento,"
				+ "estado, cidade, fotoBase64, contentType, curriculobase64, contenttypecurriculo, cpf, sexo,"
				+ "email, estadoCivil, filhos, escolaridade, profissao, fotobase64min) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setString(4, usuario.getTelefone());
			statement.setString(5, usuario.getCEP());
			statement.setString(6, usuario.getRua());
			statement.setString(7, usuario.getNumero());
			statement.setString(8, usuario.getBairro());
			statement.setString(9, usuario.getComplemento());
			statement.setString(10, usuario.getEstado());
			statement.setString(11, usuario.getCidade());
			statement.setString(12, usuario.getFotoBase64());
			statement.setString(13, usuario.getContentType());
			statement.setString(14, usuario.getCurriculoBase64());
			statement.setString(15, usuario.getContentTypeCurriculo());
			statement.setString(16, usuario.getCPF());
			statement.setString(17, String.valueOf(usuario.getSexo()));
			statement.setString(18, usuario.getEmail());
			statement.setString(19, usuario.getEstadoCivil());
			statement.setShort(20, usuario.getFilhos());
			statement.setString(21, usuario.getEscolaridade());
			statement.setString(22, usuario.getProfissao());
			statement.setString(23, usuario.getFotoBase64Min());

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
	 * Método para listar o Usuário
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<UsuarioBean> listar() throws SQLException {
		List<UsuarioBean> usuarios = new ArrayList<>();
		String sql = "select * from usuario where login <> 'admin' order by nome ";
		// select * from usuario where login <> 'admin' order by nome
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				UsuarioBean usuario = new UsuarioBean();
				usuario.setId(resultado.getLong("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				usuario.setTelefone(resultado.getString("telefone"));
				usuario.setCEP(resultado.getString("cep"));
				usuario.setRua(resultado.getString("rua"));
				usuario.setNumero(resultado.getString("numero"));
				usuario.setBairro(resultado.getString("bairro"));
				usuario.setComplemento(resultado.getString("complemento"));
				usuario.setEstado(resultado.getString("estado"));
				usuario.setCidade(resultado.getString("cidade"));
				usuario.setContentType(resultado.getString("contentType"));
				usuario.setCurriculoBase64(resultado.getString("curriculobase64"));
				usuario.setContentTypeCurriculo(resultado.getString("contenttypecurriculo"));
				usuario.setCPF(resultado.getString("cpf"));
				usuario.setSexo(resultado.getString("sexo").charAt(0));
				usuario.setEmail(resultado.getString("email"));
				usuario.setEstadoCivil(resultado.getString("estadocivil"));
				usuario.setFilhos(resultado.getShort("filhos"));
				usuario.setEscolaridade(resultado.getString("escolaridade"));
				usuario.setProfissao(resultado.getString("profissao"));
				usuario.setFotoBase64Min(resultado.getString("fotobase64min"));

				usuarios.add(usuario);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return usuarios;
	}

	/**
	 * Método que consulta o Usuário pelo ID
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public UsuarioBean consultar(Long id) throws SQLException {
		String sql = "Select * from usuario where id= '" + id + "' and login <> 'admin'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {
			UsuarioBean usuario = new UsuarioBean();
			usuario.setId(resultado.getLong("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			usuario.setSenhaConf(usuario.getSenha());
			usuario.setTelefone(resultado.getString("telefone"));
			usuario.setCEP(resultado.getString("cep"));
			usuario.setRua(resultado.getString("rua"));
			usuario.setNumero(resultado.getString("numero"));
			usuario.setBairro(resultado.getString("bairro"));
			usuario.setComplemento(resultado.getString("complemento"));
			usuario.setEstado(resultado.getString("estado"));
			usuario.setCidade(resultado.getString("cidade"));
			usuario.setFotoBase64(resultado.getString("fotoBase64"));
			usuario.setContentType(resultado.getString("contenttype"));
			usuario.setCurriculoBase64(resultado.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(resultado.getString("contenttypecurriculo"));
			usuario.setCPF(resultado.getString("cpf"));
			usuario.setSexo(resultado.getString("sexo").charAt(0));
			usuario.setEmail(resultado.getString("email"));
			usuario.setEstadoCivil(resultado.getString("estadocivil"));
			usuario.setFilhos(resultado.getShort("filhos"));
			usuario.setEscolaridade(resultado.getString("escolaridade"));
			usuario.setProfissao(resultado.getString("profissao"));
			usuario.setFotoBase64Min(resultado.getString("fotobase64min"));
			return usuario;
		}
		return null;
	}

	/**
	 * Método que edita o Usuário
	 * 
	 * @param usuario
	 * @throws SQLException
	 */
	public void atualizar(UsuarioBean usuario) throws SQLException {
		String sql = "update usuario set login = ?, nome = ?, senha = ?, telefone = ?, "
				+ "cep = ?, rua = ?, numero = ?, bairro = ?, complemento = ?, " + "estado = ?, "
				+ "cidade = ?, fotoBase64 = ?, contentType = ?, curriculobase64 = ?,"
				+ "contenttypecurriculo = ?, cpf = ?, sexo = ?, email = ?, estadocivil = ?,"
				+ "filhos = ?, escolaridade = ?, profissao = ?, fotobase64min = ? " + " where id = " + usuario.getId();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getNome());
			statement.setString(3, usuario.getSenha());
			statement.setString(4, usuario.getTelefone());
			statement.setString(5, usuario.getCEP());
			statement.setString(6, usuario.getRua());
			statement.setString(7, usuario.getNumero());
			statement.setString(8, usuario.getBairro());
			statement.setString(9, usuario.getComplemento());
			statement.setString(10, usuario.getEstado());
			statement.setString(11, usuario.getCidade());
			statement.setString(12, usuario.getFotoBase64());
			statement.setString(13, usuario.getContentType());
			statement.setString(14, usuario.getCurriculoBase64());
			statement.setString(15, usuario.getContentTypeCurriculo());
			statement.setString(16, usuario.getCPF());
			statement.setString(17, String.valueOf(usuario.getSexo()));
			statement.setString(18, usuario.getEmail());
			statement.setString(19, usuario.getEstadoCivil());
			statement.setShort(20, usuario.getFilhos());
			statement.setString(21, usuario.getEscolaridade());
			statement.setString(22, usuario.getProfissao());
			statement.setString(23, usuario.getFotoBase64Min());
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
	 * @throws SQLException
	 */
	public void delete(Long id) throws SQLException {
		String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
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
	 * Método que verifica se já existe o login registrado no banco de dados
	 * 
	 * @param login
	 * @return
	 * @throws SQLException
	 */
	public Boolean validarLogin(String login) throws SQLException {
		String sql = "Select count(1) as qtd from usuario where login= '" + login + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {
			return resultado.getInt("qtd") <= 0;
		}
		return false;
	}

}

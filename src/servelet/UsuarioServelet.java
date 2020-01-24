package servelet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.UsuarioBean;
import dao.UsuarioDAO;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/Usuario")
@MultipartConfig
public class UsuarioServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO udao = new UsuarioDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServelet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String acao = request.getParameter("acao");
		Long id = (request.getParameter("id") == null) ? -1l : Long.parseLong(request.getParameter("id"));

		try {
			if (acao.equalsIgnoreCase("delete") && acao != null) {// deleta usuários
				udao.delete(id);
				RequestDispatcher view = request.getRequestDispatcher("/cadUser.jsp");
				request.setAttribute("msg", "Usuário removido com sucesso!");
				request.setAttribute("tipo", "success");
				request.setAttribute("usuarios", udao.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {// edita usuários
				UsuarioBean user = udao.consultar(id);
				RequestDispatcher view = request.getRequestDispatcher("/cadUser.jsp");
				request.setAttribute("user", user);
				request.setAttribute("usuarios", udao.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listarTodos")) {// lista usuários
				RequestDispatcher view = request.getRequestDispatcher("/cadUser.jsp");
				request.setAttribute("msg", "Listando Usuários!");
				request.setAttribute("tipo", "info");
				request.setAttribute("usuarios", udao.listar());
				request.getSession().setAttribute("user", null);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("download")) {// download de imagem
				UsuarioBean user = udao.consultar(id);
				if (user != null) {
					String tipo = request.getParameter("tipo");
					String contentType = "";
					byte[] fileBytes = null;

					if (tipo.equalsIgnoreCase("imagem")) {
						contentType = user.getContentType();
						fileBytes = Base64.decodeBase64(user.getFotoBase64());
					} else if (tipo.equalsIgnoreCase("curriculo")) {
						contentType = user.getContentTypeCurriculo();
						fileBytes = Base64.decodeBase64(user.getCurriculoBase64());
					}
					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					OutputStream os = response.getOutputStream();
					os.write(fileBytes);

					os.flush();
					os.close();

//					byte[] imageFotoBytes = Base64.decodeBase64(user.getFotoBase64());
//
//					OutputStream os = response.getOutputStream();
//					os.write(imageFotoBytes);
//
//					os.flush();
//					os.close();

////				     outra forma de se fazer o download	
//					// converte a base64 da imagem do banco para bute[]
//					byte[] imageFotoBytes = new Base64().decodeBase64(user.getFotoBase64());
//
//					// coloca os bytes em um objeto de entrada para processar
//					InputStream inputStream = new ByteArrayInputStream(imageFotoBytes);
//
//					// inicio da resposta para o navegador
//					int read = 0;
//					byte[] bytes = new byte[1024];
//					OutputStream outputStream = response.getOutputStream();
//
//					while ((read = inputStream.read(bytes)) != -1) {
//						outputStream.write(bytes, 0, read);
//					}
//
//					outputStream.flush();
//					outputStream.close();
				}

			}
		} catch (Exception e) {
			RequestDispatcher view = request.getRequestDispatcher("/cadUser.jsp");
			request.setAttribute("msg", "Ocorreu um Erro!");
			request.setAttribute("tipo", "danger");
			try {
				request.setAttribute("usuarios", udao.listar());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			view.forward(request, response);
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String telefone = request.getParameter("fone");
		String CEP = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String numero = request.getParameter("numero");
		String bairro = request.getParameter("bairro");
		String complemento = request.getParameter("complemento");
		String estado = request.getParameter("estado");
		String cidade = request.getParameter("cidade");
		String CPF = request.getParameter("cpf");
		String sexo = request.getParameter("sexo");
		String email = request.getParameter("email");
		String estadoCivil = request.getParameter("estadoCivil");
		String filhos = request.getParameter("filhos_qtd");
		String escolaridade = request.getParameter("escolaridade");
		String profissao = request.getParameter("profissao");

		UsuarioBean usuario = new UsuarioBean();

		usuario.setId((id != null && !id.isEmpty()) ? Long.parseLong(id) : null);
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setTelefone(telefone);
		usuario.setCEP(CEP);
		usuario.setRua(rua);
		usuario.setNumero(numero);
		usuario.setBairro(bairro);
		usuario.setComplemento(complemento);
		usuario.setEstado(estado);
		usuario.setCidade(cidade);
		usuario.setCPF(CPF);
		usuario.setSexo((sexo.equalsIgnoreCase("masculino")) ? 'M' : 'F');
		usuario.setEmail(email);
		usuario.setEstadoCivil(estadoCivil);
		usuario.setFilhos((filhos != null && !filhos.isEmpty()) ? new Short(filhos) : new Short("0"));
		usuario.setEscolaridade(escolaridade);
		usuario.setProfissao(profissao);

		// acão de cancelar
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadUser.jsp");
				request.setAttribute("usuarios", udao.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// acao de salvar
		} else {

			boolean podeInserir = true;
			try {

				/* Inicio File Upload de imagens e pdf */
				if (ServletFileUpload.isMultipartContent(request)) {
					/* Upload da imagem */
					Part imagemFoto = request.getPart("foto");
					if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
						byte[] byteImagem = converteStremByte(imagemFoto.getInputStream());
						String fotoBase64 = new Base64().encodeBase64String(byteImagem);
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());

						/* inicio da criação da miniatura */

						/* transforma em um bufferedImage */
						byte[] imageByteDecoder = new Base64().decodeBase64(fotoBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecoder));

						/* Pega o tipo da imagem */
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

						/* Cria a imagem em miniatura */
						BufferedImage resizedImagem = new BufferedImage(100, 100, type);
						Graphics2D g2d = resizedImagem.createGraphics();
						g2d.drawImage(bufferedImage, 0, 0, 100, 100, null);
						g2d.dispose();

						/* escrevendo a miniatura */
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImagem, "png", baos);

						String miniaturaBase64 = "data:image/png;base64,"
								+ DatatypeConverter.printBase64Binary(baos.toByteArray());
						usuario.setFotoBase64Min(miniaturaBase64);
						/* fim da criação da miniatura */
					} else {
						if (!id.isEmpty()) {
							UsuarioBean tempUser = udao.consultar(Long.parseLong(id));
							usuario.setContentType(tempUser.getContentType());
							usuario.setFotoBase64(tempUser.getFotoBase64());
							usuario.setFotoBase64Min(tempUser.getFotoBase64Min());
						}
					}
					/* Upload do arquivo */
					Part arquivo = request.getPart("curriculo");
					if (arquivo != null && arquivo.getInputStream().available() > 0) {
						String arquivoBase64 = new Base64()
								.encodeBase64String(converteStremByte(arquivo.getInputStream()));
						usuario.setCurriculoBase64(arquivoBase64);
						usuario.setContentTypeCurriculo(arquivo.getContentType());
					} else {
						if (!id.isEmpty()) {
							UsuarioBean tempUser = udao.consultar(Long.parseLong(id));
							usuario.setContentTypeCurriculo(tempUser.getContentTypeCurriculo());
							usuario.setCurriculoBase64(tempUser.getCurriculoBase64());
						}
					}
				}

				/* Fim File Upload de imagens e pdf */

				if (id == null || id.isEmpty() && !udao.validarLogin(login)) {
					atualizarView(request, "Já existe um usuário com esse login", "info");
					podeInserir = false;
				}
				if (id == null || id.isEmpty() && udao.validarLogin(login) && podeInserir) {
					udao.salvar(usuario);
					atualizarView(request, "Dados Salvos com Sucesso!", "info");
				} else if (id != null && !id.isEmpty() && podeInserir) {
					udao.atualizar(usuario);
					atualizarView(request, "Dados Alterados com Sucesso!", "info");
				}
			} catch (Exception e) {
				request.setAttribute("msg", "Erro ao salvar o Usuário!");
				request.setAttribute("tipo", "danger");
				e.printStackTrace();
			}
			try {
				request.setAttribute("usuarios", udao.listar());
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			response.sendRedirect("usuarioSucesso.jsp");
			if (!podeInserir) {
				request.setAttribute("user", usuario);
			}
			RequestDispatcher view = request.getRequestDispatcher("/cadUser.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * Método para atualizar a view
	 * 
	 * @param request
	 * @param msg
	 * @param tipo
	 * @throws SQLException
	 */
	private void atualizarView(HttpServletRequest request, String msg, String tipo) throws SQLException {

		request.setAttribute("msg", msg);
		request.setAttribute("tipo", tipo);
	}

	/**
	 * Método que converte a entrada de fluxo de dados da imagem para bytes
	 * 
	 * @param imagem
	 * @return
	 * @throws Exception
	 */
	private byte[] converteStremByte(InputStream imagem) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();
	}

}

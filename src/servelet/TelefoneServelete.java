package servelet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.TelefoneBean;
import beans.UsuarioBean;
import dao.TelefoneDAO;
import dao.UsuarioDAO;

/**
 * Servlet implementation class TelefoneServelete
 */
@WebServlet("/Telefone")
public class TelefoneServelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO udao = new UsuarioDAO();
	private TelefoneDAO tdao = new TelefoneDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TelefoneServelete() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long idUser = Long.parseLong(request.getParameter("idUser"));
		request.getSession().setAttribute("idUser", idUser);
		String acao = request.getParameter("acao");
		try {

			if (acao.equalsIgnoreCase("delete") && acao != null) {
				Long idFone = Long.parseLong(request.getParameter("idFone"));
				tdao.delete(idFone);
				UsuarioBean usuario = udao.consultar(idUser);
				request.getSession().setAttribute("user", usuario);
				request.setAttribute("telefones", tdao.listar(usuario.getId()));
				atualizarView(request, "Telefone Removido!", "info");
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listar")) {
				UsuarioBean usuario = udao.consultar(idUser);
				request.getSession().setAttribute("user", usuario);
				request.setAttribute("user", usuario);
				request.setAttribute("telefones", tdao.listar(usuario.getId()));
				atualizarView(request, "Listando Telefones!", "info");
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				view.forward(request, response);
				System.out.println(tdao.listar(idUser).isEmpty());
			} else if (acao.equalsIgnoreCase("editar") && acao != null) {
				UsuarioBean usuario = udao.consultar(idUser);
				Long idFone = Long.parseLong(request.getParameter("idFone"));
				TelefoneBean telefone = tdao.consultar(idFone);
				request.getSession().setAttribute("user", usuario);
				request.setAttribute("telefones", tdao.listar(usuario.getId()));
				atualizarView(request, "Listando Telefones!", "info");
				request.setAttribute("user", usuario);
				request.setAttribute("tel", telefone);
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				view.forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		UsuarioBean usuario = (UsuarioBean) request.getSession().getAttribute("user");
		String idFone = request.getParameter("idFone");
		String numero = request.getParameter("fone");
		String tipo = request.getParameter("tipo");

		// acão de cancelar
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", tdao.listar(usuario.getId()));
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// acao de salvar
		} else {
			TelefoneBean telefone = new TelefoneBean();
			telefone.setId(!idFone.isEmpty() ? Long.parseLong(idFone) : null);
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setIdUser(usuario.getId());
			boolean podeInserir = false;
			try {
				if (idFone == null || idFone.isEmpty()) {
					tdao.salvar(telefone);
					atualizarView(request, "Telefone Salvos com Sucesso!", "info");
					podeInserir = true;
				} else if (idFone != null && !idFone.isEmpty()) {
					tdao.atualizar(telefone);
					atualizarView(request, "Telefone Alterados com Sucesso!", "info");
					podeInserir = true;
				}
			} catch (Exception e) {
				try {
					atualizarView(request, "Ocorreu um erro!", "info");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			try {
				request.setAttribute("telefones", tdao.listar(usuario.getId()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!podeInserir) {
				request.setAttribute("telefone", telefone);
			}
			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			view.forward(request, response);
		}
	}

	private void atualizarView(HttpServletRequest request, String msg, String tipo) throws SQLException {

		request.setAttribute("msg", msg);
		request.setAttribute("tipo", tipo);
	}

}

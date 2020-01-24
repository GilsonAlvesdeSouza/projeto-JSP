package servelet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProdutoBean;
import dao.ProdutoDAO;

/**
 * Servlet implementation class produto
 */
@WebServlet("/Produto")
public class ProdutoServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoDAO pdao = new ProdutoDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProdutoServelet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String acao = request.getParameter("acao");
		Long id = (request.getParameter("id") == null) ? -1l : Long.parseLong(request.getParameter("id"));

		try {
			if (acao.equalsIgnoreCase("delete") && acao != null) {
				pdao.delete(id);
				RequestDispatcher view = request.getRequestDispatcher("/cadProduto.jsp");
				request.setAttribute("msg", "Produto removido com sucesso!");
				request.setAttribute("tipo", "success");
				request.setAttribute("produtos", pdao.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				ProdutoBean p = pdao.consultar(id);
				RequestDispatcher view = request.getRequestDispatcher("/cadProduto.jsp");
				request.setAttribute("produto", p);
				request.setAttribute("produtos", pdao.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listarTodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadProduto.jsp");
				request.setAttribute("msg", "Listando Produtos!");
				request.setAttribute("tipo", "info");
				request.setAttribute("produtos", pdao.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher view = request.getRequestDispatcher("/cadProduto.jsp");
			request.setAttribute("msg", "Ocorreu um Erro");
			request.setAttribute("tipo", "danger");
			view.forward(request, response);
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String descricao = request.getParameter("descricao");
		Short quantidade = new Short(request.getParameter("quantidade"));
		BigDecimal preco = new BigDecimal(request.getParameter("preco").replace(".", "").replace(",", "."));
		String acao = request.getParameter("acao");

		// acão de cancelar
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadProduto.jsp");
				request.setAttribute("produtos", pdao.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// acao de salvar
		} else {
			ProdutoBean prd = new ProdutoBean();
			prd.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			prd.setDescricao(descricao);
			prd.setQuantidade(quantidade);
			prd.setPreco(preco);
			boolean podeInserir = true;
			try {
				if (id == null || id.isEmpty() && !pdao.validarLogin(descricao)) {
					atualizarView(request, "Já existe um produto com essa descrição", "info");
					podeInserir = false;
				}
				if (id == null || id.isEmpty() && pdao.validarLogin(descricao) && podeInserir) {
					pdao.salvar(prd);
					atualizarView(request, "Produto Salvos com Sucesso!", "info");
				} else if (id != null && !id.isEmpty() && podeInserir) {
					pdao.atualizar(prd);
					atualizarView(request, "Produto Alterados com Sucesso!", "info");
				}
			} catch (Exception e) {
				request.setAttribute("msg", "Erro ao cadastrar o produto!");
				request.setAttribute("tipo", "danger");
				e.printStackTrace();
			}
			try {
				request.setAttribute("produtos", pdao.listar());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			response.sendRedirect("usuarioSucesso.jsp");
			if (!podeInserir) {
				request.setAttribute("produto", prd);
			}
			RequestDispatcher view = request.getRequestDispatcher("/cadProduto.jsp");
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

}

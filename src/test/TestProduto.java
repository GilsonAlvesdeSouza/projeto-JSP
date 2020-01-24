package test;

import java.math.BigDecimal;

public class TestProduto {

	public static void main(String[] args) {
///////////////////////salvar Produto///////////////////////////////////
//		ProdutoBean p = new ProdutoBean();
//		ProdutoDAO pdao = new ProdutoDAO();
//		p.setDescricao("Macarrão");
//		p.setQuantidade(new Short("10"));
//		p.setPreco(new BigDecimal("3.25"));
//
//		try {
//			pdao.salvar(p);
//			System.out.println("Dados salvos com sucesso!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

///////////////////////////LISTA PRODUTOS///////////////////////////////////
//		List<ProdutoBean> produtos = new ArrayList<>();
//
//		try {
//			produtos = pdao.listar();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		for (ProdutoBean p1 : produtos) {
//			System.out.println(p1.getId());
//			System.out.println(p1.getDescricao());
//			System.out.println(p1.getPreco());
//			System.out.println(p1.getQuantidade());
//			System.out.println();
//		}

/////////////////////////////BUSCAR PRODUTO////////////////////////////////////

//		try {
//			p = pdao.consultar(1L);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(p.getId());
//		System.out.println(p.getDescricao());
//		System.out.println(p.getPreco());
//		System.out.println(p.getQuantidade());

////////////////////////////////ATUALIZAR PRODUTO////////////////////////////////

//		p.setId(1L);
//		p.setDescricao("Arroz");
//		p.setQuantidade(new Short("200"));
//		p.setPreco(new BigDecimal("11.25"));
//		try {
//			pdao.atualizar(p);
//			System.out.println("Atualizado com sucesso!");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			p = pdao.consultar(1L);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(p.getId());
//		System.out.println(p.getDescricao());
//		System.out.println(p.getPreco());
//		System.out.println(p.getQuantidade());

////////////////////DELETAR PRODUTO//////////////////////////////////////////////		

//		pdao.delete(3L);

		BigDecimal big1 = new BigDecimal("0.1");
		BigDecimal big2 = new BigDecimal("0.2");
		BigDecimal bigResult = big1.add(big2);
		System.out.println(bigResult.toString());
	}

}

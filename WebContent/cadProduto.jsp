<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device=width, intial-scale=1.0">
<title>Cadastro de Produtos</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link href="resources/css/cad.css" rel="stylesheet" type="text/css" />
<!--  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>-->
<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js">
	
</script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-notify.min.js">
	
</script>
<script type="text/javascript" src="resources/js/funcoes.js">
	
</script>
<script type="text/javascript" src="resources/js/jquery.mask.min.js">
	
</script>
</head>

<body>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#preco").mask("###.###.##0,00", {
				reverse : true
			})
			$("#quantidade").mask("00.000", {
				reverse : true
			})
		})
	</script>



	<script type="text/javascript">
		notify('${msg}', '${tipo}')
	</script>
	<form class="form-horizontal" action="Produto" method="post">
		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">Cadastro de Produtos</div>

				<div class="panel-body">
					<div class="form-group">
						<div class="col-md-11 control-label">
							<div class="col-md-1">
								<a href="acessliberado.jsp"><img
									src="resources/img/home1.png" title="Inicio" alt="Inicio"
									width="32px" height="32px" style="margin-left: 10px"></a> <a
									href="index.jsp"><img src="resources/img/LogOut.png"
									title="Sair" alt="Sair" width="32px" height="32px"
									style="margin-left: 10px"></a>
							</div>
							<p class="help-block">
								<h11>*</h11>
								Campo Obrigatório
							</p>
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-1 control-label" for="id">Código </label>
						<div class="col-md-1">
							<input id="id" name="id" value="${produto.id}" placeholder=""
								class="form-control input-md" readonly="readonly" type="text">
						</div>

						<label class="col-md-1 control-label" for="descricao">Descrição
							<h11>*</h11>
						</label>
						<div class="col-md-5">
							<input id="descricao" name="descricao"
								value="${produto.descricao}" placeholder="Descrição do Produto"
								class="form-control input-md" type="text" required="">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-1 control-label" for="quantidade">Quantidade
							<h11>*</h11>
						</label>
						<div class="col-md-2">
							<input id="quantidade" name="quantidade" required=""
								value="${produto.quantidade}" type="text" placeholder="0"
								class="form-control input-md">
						</div>

						<label class="col-md-1 control-label" for="preco">Preço <h11>*</h11>
						</label>
						<div class="col-md-2">
							<input id="preco" name="preco" value="${produto.preco}"
								required="" placeholder="0,00" class="form-control input-md"
								type="text">
						</div>
					</div>
					<!-- Button (Double) -->
					<div class="form-group">
						<label class="col-md-1 control-label" for="Cadastrar"></label>
						<div class="col-md-8">
							<button id="Cadastrar" name="Cadastrar" title="Salvar o Produto" class="btn btn-success"
								type="Submit">Salvar</button>
							<button id="Cancelar" title="Cancelar Edição" name="Cancelar" class="btn btn-danger"
								type="Reset" style="margin-left: 30px">Cancelar</button>
						</div>
					</div>

				</div>
			</div>
		</fieldset>
	</form>

	<div class="panel panel-primary">
		<div class="panel-heading">Listagem de Produtos</div>
	</div>


	<table class="table table-bordered">

		<thead>
			<tr>
				<th scope="col">CÓDIGO</th>
				<th scope="col">DESCRIÇÃO</th>
				<th scope="col">QUANTIDADE</th>
				<th scope="col">PREÇO</th>
				<th scope="col">OPÇÕES</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td data-label="CÓDIGO"><c:out value="${produto.id}"></c:out></td>
					<td data-label="DESCRIÇÃO"><c:out value="${produto.descricao}"></c:out></td>
					<td data-label="QUANTIDADE"><c:out
							value="${produto.quantidade}"></c:out></td>
					<td data-label="PREÇO"><c:out value="${produto.preco}"></c:out></td>
					<td data-label="EXCLUIR" width="120px"><a
						href="Produto?acao=delete&id=${produto.id}"> <img
							src="resources/img/delete.png" title="Excluir" alt="Excluir"
							width="25px" height="25px" style="margin-left: 10px">
					</a> <a href="Produto?acao=editar&id=${produto.id}"><img
							src="resources/img/lapiz.png" title="Editar" alt="Editar"
							width="25px" height="25px" style="margin-left: 30px"> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>
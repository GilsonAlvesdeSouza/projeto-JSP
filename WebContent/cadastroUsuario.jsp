<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Ususário</title>
<link rel="StyleSheet" href="resources/css/bootstrap.min.css"
	type="text/css">
<link rel="StyleSheet" href="resources/css/bootstrap-theme.min.css"
	type="text/css">
<link rel="StyleSheet" href="resources/css/tableUsuario.css">
<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.mask.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js">
</script>
<script type="text/javascript" src="resources/js/funcoes.js">
</script>
<script type="text/javascript"
	src="resources/js/bootstrap-notify.min.js">
</script>


</head>
<body>
	<div class="container">
		

		<div class="titulo">
			<label>Cadastro de Usuário</label>
		</div>
		<form id="formUser" action="salvarUsuario" method="post">
		<div>
			<label>ola</label>
			<%
				String nome = request.getParameter("msg");
			%>
			<%=nome%>
		</div>
			<script type="text/javascript">notify('${msg}', '${tipo}')</script>
			<div class="row">
				<div class="col-25">
					<label for="id">Código:</label>
				</div>
				<div class="col-75">
					<input type="text-id" readonly="readonly" id="id" name="id"
						value="${user.id}" />
				</div>
			</div>

			<div class="row">
				<div class="col-25">
					<label for="login">Nome:</label>
				</div>

				<div class="col-75">
					<input type="text" id="nome" name="nome" value="${user.nome}"
						required="" />
				</div>
			</div>

			<div class="row">
				<div class="col-25">
					<label for="fone">Telefone com DDD:</label>
				</div>

				<div class="col-75">
					<input type="text" id="telefone" name="telefone"
						value="${user.telefone}" placeholder="Ex.: (00) 00000-0000" />
				</div>
			</div>

			<div class="row">
				<div class="col-25">
					<label for="login">Login:</label>
				</div>

				<div class="col-75">
					<input type="text" id="login" name="login" value="${user.login}" />
				</div>
			</div>

			<div class="row">
				<div class="col-25">
					<label for="login">Senha: </label>
				</div>

				<div class="col-75">
					<input type="password" id="senha" name="senha"
						value="${user.senha}" />
				</div>
			</div>

			<div class="row">
				<div class="col-25">
					<label for="senhaConf">Confirmar Senha: </label>
				</div>

				<div class="col-75">
					<input type="password" id="senhaConf" name="senhaConf"
						value="${user.senhaConf}" />
				</div>
			</div>

			<div class="row">
				<input type="submit" value="Salvar" title="Salvar"> <input
					type="submit" value="Cancelar" title="Cancelar"
					onclick="document.getElement.ById('formUser').action='salvarUsuario?acao=reset">
				<button id="Cancelar" name="Cancelar" class="btn btn-danger"
					type="Reset">Cancelar</button>
			</div>
		</form>

		<script type="text/javascript">
			function validaForm() {
				var login = document.getElementById("login").value;
				var nome = document.getElementById("nome").value;
				var senha = document.getElementById("senha").value;
				var senhaConf = document.getElementById("senhaConf").value;

				if (nome == '') {
					alert("Campo 'Nome' é Obrigatório!");
					return false;
				} else if (login == '') {
					alert("campo 'Login' é Obrigatório!");
					return false;
				} else if (senha == '') {
					alert("campo 'Senha' é Obrigatório!");
					return false;
				} else if (senhaConf == '') {
					alert("campo 'Confirmar Senha' é Obrigatório!");
					return false;
				} else if (senha == senhaConf) {
					return true;
				} else {
					alert("As senhas não conferem");
					return false;
				}

			}
		</script>


		<form action="salvarUsuario" id="formPes" method="post">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<center>Listagem de Clientes</center>
				</div>
			</div>
		</form>

		<table class="table table-bordered">

			<thead>
				<tr>
					<th scope="col">CÓDIGO</th>
					<th scope="col">NOME</th>
					<th scope="col">TELEFONE</th>
					<th scope="col">LOGIN</th>
					<th scope="col">EXCLUIR</th>
					<th scope="col">EDITAR</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td data-label="CÓDIGO"><c:out value="${user.id}"></c:out></td>
						<td data-label="NOME"><c:out value="${user.nome}"></c:out></td>
						<td data-label="TELEFONE"><c:out value="${user.telefone}"></c:out></td>
						<td data-label="LOGIN"><c:out value="${user.login}"></c:out></td>
						<td data-label="EXCLUIR"><a
							href="salvarUsuario?acao=delete&user=${user.id}"><img
								src="resources/img/excluir.png" title="Excluir" alt="Excluir"
								width="25px" height="25px"></a></td>
						<td data-label="EDITAR"><a
							href="salvarUsuario?acao=editar&user=${user.id}"><img
								src="resources/img/editar.png" title="Editar" alt="Editar"
								width="25px" height="25px"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>


















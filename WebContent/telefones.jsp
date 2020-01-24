<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device=width, intial-scale=1.0">
<title>Cadastro de Telefones</title>
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
	<form id="formTel" class="form-horizontal" action="Telefone"
		method="post">
		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">Cadastro de Telefones</div>

				<div class="panel-body">
					<div class="form-group">
						<div class="col-md-11 control-label">
							<div class="col-md-2">
								<a href="acessliberado.jsp"><img
									src="resources/img/home1.png" title="Inicio" alt="Inicio"
									width="32px" height="32px" style="margin-left: 10px"></a> <a
									href="Usuario?acao=listarTodos"><img
									src="resources/img/backUser.png" title="Voltar" alt="Voltar"
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
							<input id="idUser" name="idUser" value="${user.id}"
								placeholder="" class="form-control input-md" readonly="readonly"
								type="text">
						</div>
						<label class="col-md-1 control-label" for="id">Usuário </label>
						<div class="col-md-4">
							<input id="nomeUser" name="nomeUser" value="${user.nome}"
								placeholder="" class="form-control input-md" readonly="readonly"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label" for="id">Código </label>
						<div class="col-md-1">
							<input id="idFone" name="idFone" value="${tel.id}" placeholder=""
								class="form-control input-md" readonly="readonly" type="text">
						</div>

						<label class="col-md-1 control-label" for="descricao">Número
							<h11>*</h11>
						</label>
						<div class="col-md-2">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-earphone"></i></span> <input id="fone"
									name="fone" value="${tel.numero}" class="form-control"
									placeholder="DD XXXXX-XXXX" type="text" maxlength="13"
									pattern="\[0-9]{2}\ [0-9]{4,6}-[0-9]{3,4}$"
									OnKeyPress="formatar('## #####-####', this)" required="">
							</div>
						</div>

						<label class="col-md-1 control-label" for="tipo">Tipo <h11>*</h11>
						</label>
						<div class="col-md-2">
							<select required id="tipo" name="tipo" class="form-control">
								<option value=""></option>
								<option value="Celular" ${tel.tipo == 'Celular' ? 'selected' : ''}>Celular</option>
								<option value="Residencial" ${tel.tipo == 'Residencial' ? 'selected' : ''}>Residencial</option>
								<option value="Comercial" ${tel.tipo == 'Comercial' ? 'selected' : ''}>Comercial</option>
								<option value="Contato" ${tel.tipo == 'Contato' ? 'selected' : ''}>Contato</option>
							</select>
						</div>
					</div>

					<!-- Button (Double) -->
					<div class="form-group">
						<label class="col-md-1 control-label" for="Cadastrar"></label>
						<div class="col-md-8">
							<button id="Cadastrar" name="Cadastrar" title="Salvar o Produto"
								class="btn btn-success" type="Submit">Salvar</button>
							<button id="Cancelar" title="Cancelar Edição" name="Cancelar"
								class="btn btn-danger" type="submit" style="margin-left: 30px"
								onclick="document.getElementById('formTel').action ='Telefone?acao=reset'">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
	</form>

	<div class="panel panel-primary">
		<div class="panel-heading">Listagem de Telefones</div>
	</div>


	<table class="table table-bordered">

		<thead>
			<tr>
				<th scope="col">CÓDIGO</th>
				<th scope="col">NÚMERO</th>
				<th scope="col">TIPO</th>
				<th scope="col">OPÇÕES</th>

			</tr>
		</thead>
		<tbody>

			<c:forEach items="${telefones}" var="tel">
				<tr>
					<td data-label="CÓDIGO"><c:out value="${tel.id}"></c:out></td>
					<td data-label="NUMERO"><c:out value="${tel.numero}"></c:out></td>
					<td data-label="TIPO"><c:out value="${tel.tipo}"></c:out></td>
					<td data-label="EXCLUIR" width="120px"><a
						href="Telefone?acao=delete&idFone=${tel.id}&idUser=${user.id}">
							<img src="resources/img/delete.png" title="Excluir" alt="Excluir"
							width="25px" height="25px" style="margin-left: 10px">
					</a> <a href="Telefone?acao=editar&idFone=${tel.id}&idUser=${user.id}"><img
							src="resources/img/lapiz.png" title="Editar" alt="Editar"
							width="25px" height="25px" style="margin-left: 30px"> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${telefones.isEmpty()}">
		<label>Nenhum Registro Encontrado!</label>
	</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="width=device=width, intial-scale=1.0">
<title>Cadastro de Usuários</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css" charset="utf-8">
<link href="resources/css/cad.css" rel="stylesheet" type="text/css"
	charset="utf-8" />
<!--  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>-->
<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"
	charset="utf-8">
	
</script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"
	charset="utf-8"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/js/bootstrap-notify.min.js">
	
</script>
<script type="text/javascript" src="resources/js/funcoes.js"
	charset="utf-8">
	
</script>
</head>

<body>


	<script src="resources/sweetalert/sweetalert2.all.min.js"></script>

	<script type="text/javascript" charset="utf-8">
		notify('${msg}', '${tipo}')
	</script>
	<form id="formUser" class="form-horizontal" action="Usuario"
		enctype="multipart/form-data" method="post">
		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">Cadastro de Usuários</div>

				<div class="panel-body">
					<div class="form-group">
						<div class="col-md-4">
							<a href="acessliberado.jsp"><img
								src="resources/img/home1.png" title="Inicio" alt="Inicio"
								width="32px" height="32px" style="margin-left: 10px"></a> <a
								href="index.jsp"><img src="resources/img/LogOut.png"
								title="Sair" alt="Sair" width="32px" height="32px"
								style="margin-left: 10px"></a>
						</div>
						<div class="col-md-11 control-label">

							<p class="help-block">
								<h11>*</h11>
								Campo Obrigatório
							</p>
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="id">Código </label>
						<div class="col-md-1">
							<input id="id" name="id" value="${user.id}" placeholder=""
								class="form-control input-md" readonly="readonly" type="text">
						</div>
						<label class="col-md-1 control-label" for="nome">Nome <h11>*</h11></label>
						<div class="col-md-3">
							<input id="nome" name="nome" value="${user.nome}"
								placeholder="Nome Completo" class="form-control input-md"
								required="" type="text">
						</div>
						<label class="col-md-1 control-label" for="login">Login <h11>*</h11></label>
						<div class="col-md-3">
							<input id="login" name="login" value="${user.login}" required=""
								placeholder="Informe um login" class="form-control input-md"
								type="text">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-2 control-label" for="senha">Senha <h11>*</h11></label>
						<div class="col-md-2">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input id="senha"
									name="senha" value="${user.senha}" type="password" required=""
									placeholder="Digite a senha" class="form-control input-md">
							</div>
						</div>
						<label class="col-md-1 control-label" for="confSenha">Confirmar
							<h11>*</h11>
						</label>
						<div class="col-md-2">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input id="confSenha"
									name="confSenha" value="${user.senha}" required=""
									placeholder="Confime a senha" class="form-control input-md"
									type="password">
							</div>
						</div>

						<!-- Multiple Radios (inline) -->
						<label class="col-md-1 control-label" for="radios">Sexo <h11>*</h11></label>
						<div class="col-md-4">
							<label required="" class="radio-inline" for="radios-0"> <input
								name="sexo" id="sexo" value="feminino" type="radio" required=""
								${user.sexo == 'F'.charAt(0) ? 'checked' : ''}> Feminino
							</label> <label class="radio-inline" for="radios-1"> <input
								name="sexo" id="sexo" value="masculino" type="radio"
								${user.sexo == 'M'.charAt(0) ? 'checked' : ''}>
								Masculino
							</label>
						</div>
					</div>


					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Nome">CPF <h11>*</h11></label>
						<div class="col-md-2">
							<input id="cpf" name="cpf" placeholder="Apenas números"
								required="" value="${user.CPF}" class="form-control input-md"
								type="text" maxlength="14"
								pattern="[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}+$"
								OnKeyPress="formatar('###.###.###-##', this)">
						</div>

						<label class="col-md-1 control-label" for="Nome">Nascimento<h11>*</h11></label>
						<div class="col-md-2">
							<input id="dtnasc" name="dtnasc" placeholder="DD/MM/AAAA"
								class="form-control input-md" type="text" maxlength="10"
								OnKeyPress="formatar('##/##/####', this)" onBlur="showhide()">
						</div>

						<label class="col-md-1 control-label" for="foto">Foto</label>
						<div class="col-md-3">
							<input id="foto" name="foto" value=""
								placeholder="Informe um login" class="form-control input-md"
								type="file">
						</div>
					</div>

					<!-- Prepended text-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="fone">Celular <h11>*</h11>
						</label>
						<div class="col-md-2">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-earphone"></i></span> <input id="fone"
									name="fone" value="${user.telefone}" class="form-control"
									placeholder="DD XXXXX-XXXX" type="text" maxlength="13"
									pattern="\[0-9]{2}\ [0-9]{4,6}-[0-9]{3,4}$"
									OnKeyPress="formatar('## #####-####', this)">
							</div>
						</div>

						<label class="col-md-1 control-label" for="prependedtext">Telefone</label>
						<div class="col-md-2">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-earphone"></i></span> <input
									id="prependedtext" name="prependedtext" class="form-control"
									placeholder="DD XXXXX-XXXX" type="text" maxlength="13"
									pattern="\[0-9]{2}\ [0-9]{4,6}-[0-9]{3,4}$"
									OnKeyPress="formatar('## #####-####', this)">
							</div>

						</div>
						<label class="col-md-1 control-label" for="curriculo">Curriculo</label>
						<div class="col-md-3">
							<input id="curriculo" name="curriculo" value=""
								placeholder="Informe um login" class="form-control input-md"
								type="file">
						</div>

					</div>

					<!-- Prepended text-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="email">Email </label>
						<div class="col-md-5">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input
									value="${user.email}" id="email" name="email"
									class="form-control" placeholder="email@email.com" type="text"
									pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
							</div>
						</div>
					</div>

					<!-- Search input-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="cep">CEP <h11>*</h11></label>
						<div class="col-md-2">
							<input id="cep" name="cep" placeholder="00000-000"
								class="form-control input-md" required="" value="${user.CEP}"
								type="search" maxlength="9" pattern="[0-9]{5}-[0-9]{3}+$"
								OnKeyPress="formatar('#####-###', this)"
								onblur="pesquisacep(cep.value)">
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary"
								title="Pesquisar CEP" onclick="pesquisacep(cep.value)">Pesquisar</button>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary"
								onclick="Swal.fire({
								  title: 'Tem Certeza que deseja remover esse Item?',
								  text: 'Confirme a Opção!',
								  type: 'warning',
								  showCancelButton: true,
								  confirmButtonColor: '#3085d6',
								  cancelButtonColor: '#d33',
								  confirmButtonText: 'Sim',
								  cancelButtonText:'Nao'										  
								}).then((result) => {
								  if (result.value) {
								   
								  }
								})"
								id="tombol">Sweet</button>
						</div>
					</div>

					<!-- Prepended text-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="prependedtext">Endereço</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon">Rua</span> <input id="rua"
									value="${user.rua}" name="rua" class="form-control"
									placeholder="" required="" readonly="readonly" type="text">
							</div>

						</div>
						<div class="col-md-2">
							<div class="input-group">
								<span class="input-group-addon">Nº <h11>*</h11></span> <input
									id="numero" name="numero" class="form-control" placeholder=""
									required="" type="text" value="${user.numero}">
							</div>

						</div>

						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">Bairro</span> <input id="bairro"
									name="bairro" class="form-control" placeholder="" required=""
									readonly="readonly" type="text" value="${user.bairro}">
							</div>

						</div>
					</div>

					<div class="form-group">
						<label class="col-md-2 control-label" for="prependedtext"></label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon">Complemento</span> <input
									id="complemento" name="complemento" class="form-control"
									placeholder="" type="text" value="${user.complemento}">
							</div>
						</div>

						<div class="col-md-2">
							<div class="input-group">
								<span class="input-group-addon">Estado</span> <input id="estado"
									name="estado" class="form-control" placeholder="" required=""
									readonly="readonly" type="text" value="${user.estado}">
							</div>
						</div>

						<label class="col-md-2 control-label" for="prependedtext"></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">Cidade</span> <input id="cidade"
									name="cidade" class="form-control" placeholder="" required=""
									readonly="readonly" type="text" value="${user.cidade}">
							</div>
						</div>

					</div>

					<!-- Select Basic -->
					<div class="form-group">
						<label class="col-md-2 control-label" for="estadoCivil">Estado
							Civil <h11>*</h11>
						</label>
						<div class="col-md-2">
							<select required id="estadoCivil" name="estadoCivil"
								class="form-control">
								<option value=""></option>
								<option value="Solteiro(a)"
									${user.estadoCivil == 'Solteiro(a)' ? 'selected' : ''}>Solteiro(a)</option>
								<option value="Casado(a)"
									${user.estadoCivil == 'Casado(a)' ? 'selected' : ''}>Casado(a)</option>
								<option value="Divorciado(a)"
									${user.estadoCivil == 'Divorciado(a)' ? 'selected' : ''}>Divorciado(a)</option>
								<option value="Viuvo(a)"
									${user.estadoCivil == 'Viuvo(a)' ? 'selected' : ''}>Viuvo(a)</option>
							</select>
						</div>

						<!-- Prepended checkbox -->

						<label class="col-md-1 control-label" for="Filhos">Filhos<h11>*</h11></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <label
									class="radio-inline" for="radios-0"> <input
										type="radio" name="filhos" id="filhos" value="nao"
										onclick="desabilita('filhos_qtd')" required=""
										${user.filhos == 0 ? 'checked' : ''}> Não
								</label> <label class="radio-inline" for="radios-1"> <input
										type="radio" name="filhos" id="filhos" value="sim"
										onclick="habilita('filhos_qtd')"
										${user.filhos > 0 ? 'checked' : ''}> Sim
								</label>
								</span> <input id="filhos_qtd" name="filhos_qtd" value="${user.filhos}"
									class="form-control" type="text" placeholder="Quantos?"
									pattern="[0-9]+$">

							</div>

						</div>
					</div>


					<!-- Select Basic -->
					<div class="form-group">

						<label class="col-md-2 control-label" for="selectbasic">Escolaridade
							<h11>*</h11>
						</label>

						<div class="col-md-3">
							<select required="" id="escolaridade" name="escolaridade"
								class="form-control">
								<option value=""></option>
								<option value="Analfabeto"
									${user.escolaridade == 'Analfabeto' ? 'selected' : ''}>Analfabeto</option>
								<option value="Fundamental Incompleto"
									${user.escolaridade == 'Fundamental Incompleto' ? 'selected' : ''}>Fundamental
									Incompleto</option>
								<option value="Fundamental Completo">Fundamental
									Completo</option>
								<option value="Médio Incompleto"
									${user.escolaridade == 'Médio Incompleto' ? 'selected' : ''}>Médio
									Incompleto</option>
								<option value="Médio Completo"
									${user.escolaridade == 'Médio Completo' ? 'selected' : ''}>Médio
									Completo</option>
								<option value="Superior Incompleto"
									${user.escolaridade == 'Superior Incompleto' ? 'selected' : ''}>Superior
									Incompleto</option>
								<option value="Superior Completo"
									${user.escolaridade == 'Superior Completo' ? 'selected' : ''}>Superior
									Completo</option>
							</select>
						</div>


						<!-- Text input-->

						<label class="col-md-1 control-label" for="profissao">Profissão</label>
						<div class="col-md-4">
							<input id="profissao" name="profissao" type="text" placeholder=""
								value="${user.profissao}" class="form-control input-md">

						</div>
					</div>

					<!-- Button (Double) -->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Cadastrar"></label>
						<div class="col-md-8">
							<button id="Cadastrar" name="Cadastrar" title="Salvar Usuário"
								class="btn btn-success" type="submit">Salvar</button>
							<button id="Cancelar" name="Cancelar" title="Cancelar Edição"
								class="btn btn-danger" type="submit" style="margin-left: 30px"
								onclick="document.getElementById('formUser').action = 'Usuario?acao=reset'">Cancelar</button>
						</div>
					</div>

				</div>
			</div>
		</fieldset>
	</form>

	<div class="panel panel-primary">
		<div class="panel-heading">Listagem de Usuários</div>
	</div>


	<table class="table table-bordered">

		<thead>
			<tr>
				<th scope="col">CÓDIGO</th>
				<th scope="col">FOTO</th>
				<th scope="col">CURRICULO</th>
				<th scope="col">NOME</th>
				<th scope="col">LOGIN</th>
				<th scope="col">TELEFONE</th>
				<th scope="col">OPÇÕES</th>

			</tr>
		</thead>
		<tbody>

			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td data-label="CÓDIGO"><c:out value="${user.id}"></c:out></td>
					<c:if test="${user.fotoBase64Min.isEmpty() == false }">
						<td data-label="FOTO"><a
							href="Usuario?acao=download&tipo=imagem&id=${user.id}"><img
								src='<c:out value="${user.fotoBase64Min}"/>' title="imagem"
								alt="imagem" width="30px" height="30px"
								style="margin-left: 10px"></a></td>
					</c:if>
					<c:if
						test="${user.fotoBase64Min.isEmpty() == true || user.fotoBase64Min == null}">
						<td><img alt="ImagemUser" src="resources/img/Userimage.png"
							style="margin-left: 10px" width="30px" height="30px"></td>
					</c:if>

					<c:if test="${user.curriculoBase64.isEmpty() == false}">
						<td data-label="CURRICULO"><a
							href="Usuario?acao=download&tipo=curriculo&id=${user.id}"><img
								src="resources/img/Downloadpdf.png" title="curriculo"
								alt="curriculo" width="30px" height="30px"
								style="margin-left: 10px"></a></td>
					</c:if>
					<c:if
						test="${user.curriculoBase64.isEmpty() == true || user.curriculoBase64 == null}">
						<td><img alt="ImagemUser" src="resources/img/file-empty.png"
							style="margin-left: 10px" width="30px" height="30px"></td>
					</c:if>

					<td data-label="NOME"><c:out value="${user.nome}"></c:out></td>
					<td data-label="LOGIN"><c:out value="${user.login}"></c:out></td>
					<td data-label="TELEFONE"><c:out value="${user.telefone}"></c:out></td>
					<td data-label="EXCLUIR" width="200px"><a
						href="Usuario?acao=delete&id=${user.id}"onclick="return confirm('confirmar a exclusão?');"> <img 
							src="resources/img/delete.png" title="Excluir" alt="Excluir"
							width="25px" height="25px" style="margin-left: 10px">
					</a> <a href="Usuario?acao=editar&id=${user.id}"><img
							src="resources/img/lapiz.png" title="Editar" alt="Editar"
							width="25px" height="25px" style="margin-left: 30px"> </a> </a> <a
						href="Telefone?acao=listar&idUser=${user.id}"><img
							src="resources/img/telefone.png" title="Telefones"
							alt="Telefones" width="25px" height="25px"
							style="margin-left: 30px"> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
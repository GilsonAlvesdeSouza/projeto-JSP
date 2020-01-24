<jsp:useBean id="calcula" class="beans.UsuarioBean"
	type="beans.UsuarioBean" scope="page" />

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistema de Cadastro JSP</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="resources/css/login.css">
<script type="text/javascript" src="resources/js/bootstrap.min.js"
	charset="utf-8"></script>
<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"
	charset="utf-8"></script>
</head>
<body>
	<!--<div class="login-page">
		<div class="form">
			<form id="formCad" action="LoginServelet" method="post"
				class="login-form">
				<output>
				<h3>Crud JSP</h3>
				</output>
				<input type="text" id="login" placeholder="Nome do Usuário"
					name="login"> <br> <input type="password" id="senha"
					placeholder="Senha" name="senha"><br>
				<button type="submit">Entrar</button>
			</form>
		</div>
	</div>-->


	 <div class="login-body">
		<article class="container-login center-block"> <section>

		<div
			class="tab-content tabs-login col-lg-12 col-md-12 col-sm-12 cols-xs-12">
			<div id="login-access" class="tab-pane fade active in">
				<h2>
					<i class="glyphicon glyphicon-log-in"></i> Acesso
				</h2>
				<form id="formCad" action="LoginServelet" method="post" accept-charset="utf-8" autocomplete="off"
					role="form" class="form-horizontal">
					<div class="form-group ">
						<label for="login" class="sr-only">Login</label> <input required=""
							type="text" class="form-control" name="login" id="login_value"
							placeholder="Login" tabindex="1" value="" />
					</div>
					<div class="form-group ">
						<label for="senha" class="sr-only">Senha</label> <input required=""
							type="password" class="form-control" name="senha"
							id="senha" placeholder="Senha" value="" tabindex="2" />
					</div>
					<div class="checkbox">
						<label class="control-label" for="remember_me"> <input 
							type="checkbox" name="remember_me" id="remember_me" value="1"
							class="" tabindex="3" /> Lembrar
						</label>
					</div>
					<br />
					<div class="form-group ">
						<button type="submit" name="log-me-in" id="submit" tabindex="5"
							class="btn btn-lg btn-primary">Entrar</button>
					</div>
				</form>
			</div>
		</div>
		</section> </article>
	</div>

</body>
</html>
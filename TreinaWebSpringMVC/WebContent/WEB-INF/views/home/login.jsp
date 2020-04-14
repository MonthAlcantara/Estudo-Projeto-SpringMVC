<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<title> Tela de Login</title>
<h2> Login</h2>

<form action="/TreinaWebSpringMVC/login" method="post"><!-- Na config do meu security eu no auto-config=true, significa que o security deverá fazer o porocesso de autenticação pela url /login  -->
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label>Nome:</label>
				<input type="text" name="username"><!-- Na config  do security eu disse que meu username viria de um  username-parameter >>"username"-->
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label>Senha:</label>
			<input type="password" name="password"/><!-- Na config  do security eu disse que meu username viria de um  password-parameter >>"username"-->
			</div>
		</div>
	</div>
	<input type="submit" value="Fazer login" class="btn btn-default" />
</form>

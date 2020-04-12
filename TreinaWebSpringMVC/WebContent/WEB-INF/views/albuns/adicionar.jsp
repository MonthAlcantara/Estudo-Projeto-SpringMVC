<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 	TAGLIB do JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- TAGLIB do Spring -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- Criando uma referencia para a action em var-->
<c:url var="actionAdicionar" value="/albuns/adicionar"></c:url>
<!-- associado a action (var) por post ao formul�rio que ser� enviado para o servidor -->

<title>Adi��o de Albuns</title>
<h2> Adicione um novo �lbum</h2>
<br/>

<!-- A acao do form vai ser chamar a url que eu criei acima com nome de actionAdicionar -->
<form:form action="${actionAdicionar}" method="post"
	modelAttribute="album">
	<!-- Essa fun��o cria um formulario ja de maneira integrada ao SpringMVC-->

	<div class="row">
		<div class="col-md-6">
			<!-- Especificando que este elemento ocupar� em dispositivos de media resolu��o (md) 06 das 12 divis�es dessa linha -->
			<div class="form-group">
				<label>Nome:</label>
				<form:input path="nome" cssClass="form-control" />
				<!-- O form do spring me permite associar meu input a uma classe css. Essa classe estilizar� o input. � uma classe pronta do Bootstrap -->
				<form:errors path="nome" cssStyle="color: red;" />

			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<!-- div class="col-md-6 col-xs-12" = Para dispositivos medios ele ocupara 6 colunas mas em disposivos pequenos (xs) ele ocupar� 12  colunas. o pr�prio bootstrap vai fazer essa distin��o do parelho do usuario -->
				<label>Ano de Lan�amento:</label>
				<form:input path="anoDeLancamento" cssClass="form-control" />
				<form:errors path="anoDeLancamento" cssStyle="color: red;" />
			</div>
		</div>
	</div>
	<input type="submit" value="Salvar" class="btn btn-default" />
	<!-- A classe btn � do proprio html por�m ela identifica o elemento como bot�o para o Bootstrap e permite estilizar ele -->

</form:form>

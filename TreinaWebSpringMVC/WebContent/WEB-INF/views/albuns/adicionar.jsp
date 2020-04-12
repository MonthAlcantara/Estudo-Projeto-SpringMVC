<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 	TAGLIB do JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- TAGLIB do Spring -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- Criando uma referencia para a action em var-->
<c:url var="actionAdicionar" value="/albuns/adicionar"></c:url>
<!-- associado a action (var) por post ao formulário que será enviado para o servidor -->

<title>Adição de Albuns</title>
<h2> Adicione um novo Álbum</h2>
<br/>

<!-- A acao do form vai ser chamar a url que eu criei acima com nome de actionAdicionar -->
<form:form action="${actionAdicionar}" method="post"
	modelAttribute="album">
	<!-- Essa função cria um formulario ja de maneira integrada ao SpringMVC-->

	<div class="row">
		<div class="col-md-6">
			<!-- Especificando que este elemento ocupará em dispositivos de media resolução (md) 06 das 12 divisões dessa linha -->
			<div class="form-group">
				<label>Nome:</label>
				<form:input path="nome" cssClass="form-control" />
				<!-- O form do spring me permite associar meu input a uma classe css. Essa classe estilizará o input. É uma classe pronta do Bootstrap -->
				<form:errors path="nome" cssStyle="color: red;" />

			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<!-- div class="col-md-6 col-xs-12" = Para dispositivos medios ele ocupara 6 colunas mas em disposivos pequenos (xs) ele ocupará 12  colunas. o próprio bootstrap vai fazer essa distinção do parelho do usuario -->
				<label>Ano de Lançamento:</label>
				<form:input path="anoDeLancamento" cssClass="form-control" />
				<form:errors path="anoDeLancamento" cssStyle="color: red;" />
			</div>
		</div>
	</div>
	<input type="submit" value="Salvar" class="btn btn-default" />
	<!-- A classe btn é do proprio html porém ela identifica o elemento como botão para o Bootstrap e permite estilizar ele -->

</form:form>

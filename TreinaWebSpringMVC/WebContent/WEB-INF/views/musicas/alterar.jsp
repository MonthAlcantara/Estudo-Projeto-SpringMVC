<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 	TAGLIB do JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- TAGLIB do Spring -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- Criando uma referencia para a action em var-->
<c:url var="actionAdicionar" value="/musicas/adicionar"></c:url>
<!-- associado a action (var) por post ao formul�rio que ser� enviado para o servidor -->

<title>Adi��o de Musicas</title>
<h2>Edi��o da Musica "${musica.nome}"</h2>
<br />

<!-- A acao do form vai ser chamar a url que eu criei acima com nome de actionAdicionar -->
<form:form action="${actionAdicionar}" method="post"
	modelAttribute="musica">
	<!-- Essa fun��o cria um formulario ja de maneira integrada ao SpringMVC-->


	<div class="row">
		<div class="col-md-6">
			<!-- Especificando que este elemento ocupar� em dispositivos de media resolu��o (md) 06 das 12 divis�es dessa linha -->
			<div class="form-group">
				<label>id:</label>
				<form:input path="id" cssClass="form-control" readonly="true" />
				</div>
		</div>
	</div>
		<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<!-- div class="col-md-6 col-xs-12" = Para dispositivos medios ele ocupara 6 colunas mas em disposivos pequenos (xs) ele ocupar� 12  colunas. o pr�prio bootstrap vai fazer essa distin��o do parelho do usuario -->
				<label>Nome:</label>
				<form:input path="nome" cssClass="form-control" />
				<form:errors path="nome" cssStyle="color: red;" />
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<!-- div class="col-md-6 col-xs-12" = Para dispositivos medios ele ocupara 6 colunas mas em disposivos pequenos (xs) ele ocupar� 12  colunas. o pr�prio bootstrap vai fazer essa distin��o do parelho do usuario -->
				<label>Data de Cria��o:</label>
				<form:input path="dataCriacao" cssClass="form-control" />
				<form:errors path="dataCriacao" cssStyle="color: red;" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label>Album</label>
				<form:select path="album.id" cssClass="form-control">
					<!-- Path � de onde ser� selecionado. Nesse caso seria do id do atributo Album, da classe Musica -->
					<form:options items="${albuns}" itemLabel="nome" itemValue="id" />
					<!-- items = De onde v�o vim essas op��es. Nesse casp vem da lista albuns fornecida pela action adicionar do method Get -->
					<!-- As op��es ser�o de albuns. Qual atributo que vai aparecer nas op��es � o ItemLabel ="atributoNome" -->
					<!-- itemValue =  valor que a op��o path ir� assumir -->
				</form:select>
			</div>
		</div>

	</div>
	<input type="submit" value="Salvar" class="btn btn-default" />
	<!-- A classe btn � do proprio html por�m ela identifica o elemento como bot�o para o Bootstrap e permite estilizar ele -->

</form:form>

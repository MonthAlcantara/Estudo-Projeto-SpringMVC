<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>Listagem de Usuarios</h2>
<br />
<div class="row">
	<div class="col-md-10">
		<div class="form-group">
			<label>Musica a ser pesquisada:</label> <input type="text"
				id="txt-pesquisa" class="form-control">
		</div>
		<button class="btn btn-default" id="btn-pesquisar">Pesquisar</button>
		<!-- Eu devo fazer que o evento deste botão seja chamar a minha action pesquisar por nome Passando o nome digitado no meu input -->
	</div>
</div>
<table class="table" id="tbl-musicas">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nome do Usuario</th>
			<th>Roler</th>

		</tr>
	</thead>

	<tbody>
		<c:if test="${!empty usuarios}">
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.username}</td>
					<td>${usuario.roler}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<a href="/TreinaWebSpringMVC/usuarios/adicionar/" class="btn btn-default">Adicionar
	novo Usuario</a>

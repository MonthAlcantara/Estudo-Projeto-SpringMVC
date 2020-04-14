<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>Listagem de Usuarios</h2>
<br />
<table class="table" id="tbl-musicas">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nome do Usuario</th>
			<th>Role</th>

		</tr>
	</thead>

	<tbody>
		<c:if test="${!empty usuarios}">
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.username}</td>
					<td>${usuario.role}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<a href="/TreinaWebSpringMVC/usuarios/adicionar/" class="btn btn-default">Adicionar
	novo Usuario</a>

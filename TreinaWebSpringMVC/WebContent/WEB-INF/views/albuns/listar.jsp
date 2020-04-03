<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Listagem de Álbuns</h2>
<br/>
<table class="table">
<thead>
<tr>
<th>ID</th>
<th>Nome do Album</th>
<th>Ano de Lançamento</th>
<th>Ações</th>
</tr>
</thead>

<tbody>
<c:if test="${!empty albuns}">
<c:forEach items="${albuns}" var="album">
<tr>
<td><c:out value="${album.id}"/></td>
<td><c:out value="${album.nome}"/></td>
<td><c:out value="${album.anoDeLancamento}"/></td>
<td><a href="/TreinaWebSpringMVC/albuns/alterar/${album.id}">Alterar</a> | <a href="/TreinaWebSpringMVC/albuns/excluir/${album.id}">Excluir</a></td>
</tr>
</c:forEach>
</c:if>
</tbody>
</table>
<a href="/TreinaWebSpringMVC/albuns/adicionar/" class="btn btn-default">Adicionar album</a>
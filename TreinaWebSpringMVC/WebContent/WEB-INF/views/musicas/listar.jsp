<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Listagem de Musicas</h2>
<br/>
<table class="table">
<thead>
<tr>
<th>ID</th>
<th>Nome da Música</th>
<th>Data de Criação</th>
<th>Nome do Album</th>
<th>Ações</th>
</tr>
</thead>

<tbody>
<c:if test="${!empty musicas}">
<c:forEach items="${musicas}" var="musica">
<tr>
<td><c:out value="${musica.id}"/></td>
<td><c:out value="${musica.nome}"/></td>
<td><c:out value="${musica.dataCriacao}"/></td>
<td><c:out value="${musica.album.nome}"/></td>
<td><a href="/TreinaWebSpringMVC/musicas/alterar/${musica.id}">Alterar</a> | <a href="/TreinaWebSpringMVC/musicas/excluir/${musica.id}">Excluir</a></td>
</tr>
</c:forEach>
</c:if>
</tbody>
</table>
<a href="/TreinaWebSpringMVC/musicas/adicionar/" class="btn btn-default">Adicionar nova Musica</a>
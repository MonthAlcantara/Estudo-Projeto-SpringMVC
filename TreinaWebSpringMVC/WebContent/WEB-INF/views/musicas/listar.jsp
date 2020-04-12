<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<td>${musica.id}</td>
<td>${musica.nome}</td>
<td><fmt:formatDate value="${musica.dataCriacao}" pattern="dd/MM/yyyy"/></td><!-- fmt, taglib do java para formatar data. Pattern é o padrão que eu quero -->
<td>${musica.album.nome}</td>
<td><a href="/TreinaWebSpringMVC/musicas/alterar/${musica.id}">Alterar</a> | <a href="/TreinaWebSpringMVC/musicas/excluir/${musica.id}">Excluir</a></td>
</tr>
</c:forEach>
</c:if>
</tbody>
</table>
<a href="/TreinaWebSpringMVC/musicas/adicionar/" class="btn btn-default">Adicionar nova Musica</a>
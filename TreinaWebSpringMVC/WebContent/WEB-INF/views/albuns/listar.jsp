<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Listagem de Álbuns</h2>
<br />
<div class="row">
	<div class="col-md-10">
	<div class="form-group">
		<label>Álbum a ser pesquisado:</label> <input type="text"
			id="txt-pesquisa" class="form-control">
	</div>
	<button class="btn btn-default" id="btn-pesquisar">Pesquisar</button>
	<!-- Eu devo fazer que o evento deste botão seja chamar a minha action pesquisar por nome Passando o nome digitado no meu input -->
</div>
</div>
<table class="table" id="tbl-albuns">
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
					<td><c:out value="${album.id}" /></td>
					<td><c:out value="${album.nome}" /></td>
					<td><c:out value="${album.anoDeLancamento}" /></td>
					<td><a href="/TreinaWebSpringMVC/albuns/alterar/${album.id}">Alterar</a>
						| <a href="/TreinaWebSpringMVC/albuns/excluir/${album.id}">Excluir</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<a href="/TreinaWebSpringMVC/albuns/adicionar/" class="btn btn-default">Adicionar
	album</a>
	<!--Aqui dentro eu usarei Jquery. Posso fazer isso por que o meu baseLayout ja possui a declaração do Jquery -->
<script type="text/javascript">

	$(document).ready(function(){
		$('#btn-pesquisar').click(function(){
			var nomeAlbum = $('#txt-pesquisa').val();
			$.ajax({
				method : 'GET',
				url : '/TreinaWebSpringMVC/albuns/porNome?nome=' + nomeAlbum,
				success: function(data){
					
				$('#tbl-albuns tbody > tr').remove(); 
				$.each(data, function(index, album){
					$('#tbl-albuns tbody').append(	
							'<tr>' +
							'<td>' + album.id + '</td>' +
							'<td>' + album.nome + '</td>' +
							'<td>' + album.anoDeLancamento + '</td>' +
							'<td>' + 
							'<a href="/TreinaWebSpringMVC/albuns/alterar/'+ album.id +'">Alterar </a> |'+ 
							'<a href="/TreinaWebSpringMVC/albuns/excluir/'+ album.id +'">Excluir </a>' + 
							'</td>' +
							'</tr>'
							);	
				});
								
				},
				error: function(){
					alert("Houve um erro na requisição.");
				}
			});
		});
	});
	</script>
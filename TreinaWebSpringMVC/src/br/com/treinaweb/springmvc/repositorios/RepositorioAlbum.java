package br.com.treinaweb.springmvc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.treinaweb.springmvc.dominios.Album;

public interface RepositorioAlbum extends JpaRepository<Album, Long>{
	/*Essa interface estende a classe JpaRository e � passado<O nome da classe entidade, Tipo da chave primaria da entidade */
	/*Essa classe JpaRepository � fornecida pelo SpringData e ja posssui todos os m�todos do CRUD */
	/*Em meu controler eu crio um objeto deste tipo onde vai me permitir ter acesso aos metodos de acesso ao banco*/	

	@Query("Select a from Album a where a.nome like %:nome%")//JPQL
	List<Album> findByNome(@Param("nome") String nome); //Como minha JpaRepository n�o contempla pesquisa por nome. Essa simples declara��o faz com que
	//...o springData crie um metodo que faca a busca por nome. "findBy" j� � o esperado e logo ap�s eu coloco o nome do atributo que quero pesquisar
	//... no caso um atributo de Album. Ele ja vai entender que no meu dom�nio Album tem o atributo nome e que ele deve pesquisar por (findBy) esse atributo
	//O (String nome) ser� o nome que ele vai pesquisar
}

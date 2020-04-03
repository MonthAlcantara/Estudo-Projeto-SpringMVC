package br.com.treinaweb.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.springmvc.dominios.Album;

public interface RepositorioAlbum extends JpaRepository<Album, Long>{
	/*Essa interface estende a classe JpaRository e é passado<O nome da classe entidade, Tipo da chave primaria da entidade */
	/*Essa classe JpaRepository é fornecida pelo SpringData e ja posssui todos os métodos do CRUD */
	/*Em meu controler eu crio um objeto deste tipo onde vai me permitir ter acesso aos metodos de acesso ao banco*/	

}

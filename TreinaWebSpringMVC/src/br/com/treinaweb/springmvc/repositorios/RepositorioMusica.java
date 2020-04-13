package br.com.treinaweb.springmvc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.treinaweb.springmvc.dominios.Musica;
@Repository
public interface RepositorioMusica extends JpaRepository< Musica, Long>{
	
	@Query("Select m FROM Musica m WHERE m.nome like %:nome%")
	List<Musica> findByNome(@Param("nome")String nome);
	
}

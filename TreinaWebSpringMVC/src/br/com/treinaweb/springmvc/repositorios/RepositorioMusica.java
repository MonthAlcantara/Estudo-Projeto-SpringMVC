package br.com.treinaweb.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treinaweb.springmvc.dominios.Musica;
@Repository
public interface RepositorioMusica extends JpaRepository< Musica, Long>{

}

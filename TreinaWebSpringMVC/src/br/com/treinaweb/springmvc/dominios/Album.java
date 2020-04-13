package br.com.treinaweb.springmvc.dominios;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity // Esta classe é uma entidade que pode ser persistida em BD
@Table(name = "alb_albuns") // O nome da tabela referente a essa entity é "alb_albuns"
//Classe Pojo. Uma Classe que possui suas variáveis de forma privada e métodos getters e setters públicos.
public class Album {

	@Id // Estou informando que essa variável será meu id
	@Column(name = "alb_id") // Essa variável será uma coluna no meu banco e terá o nome descrito em "name"
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ovalor dessa variável será gerada automaticamente
	private Long id;

	@NotNull(message = " Voce deve preencher esse campo")
	@NotEmpty(message = " Voce deve preencher esse campo")
	@Column(name = "alb_nome", length = 50, nullable = false) // largura (length) máximo de caracteres = 50, não pode
	private String nome;

	@NotNull(message = "Voce deve preencher esse campo")
	@Min(value = 1990, message = "O ano de lançamento deve ser maior que 1990")
	@Max(value = 2020, message = "O ano de lançamento deve ser menor que 2020")
	@Column(name = "alb_ano_lancamento", length = 4, nullable = false)
	private int anoDeLancamento;

	//UmPraMuitos(mappedBy="Dentro da entidade musica, quem está mapeado com relaçao a entidade album, que no caso é a variável album",cascade="O que acontecerá com esse set ao ser atualizado o album)
	@OneToMany(mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)//orphanRemoval = remove musicas que não tenham um album correspondente automaticamente
	@JsonBackReference
	private Set<Musica> musicas;

	public Set<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(Set<Musica> musicas) {
		this.musicas = musicas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnoDeLancamento() {
		return anoDeLancamento;
	}

	public void setAnoDeLancamento(int anoDeLancamento) {
		this.anoDeLancamento = anoDeLancamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

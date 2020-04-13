package br.com.treinaweb.springmvc.dominios;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "mus_musicas")
public class Musica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mus_id")
	private Long id;
	
	@Column(name = "mus_nome", nullable = false, length = 10)
	@Size(min=3, max=10,message = "O nome da m�sica deve conter entre 3 e 10 caracteres" )
	@NotEmpty(message = "O nome � obrigat�rio")//N�o pode ser vazio
	@NotNull(message = "O nome � obrigat�rio")//N�o pode ser nulo
	private String nome;
	
	@Column(name="mus_data_criacao", nullable = false)//nullable false, n�o pode ser nulo
	@Temporal(TemporalType.DATE)//por esse meu atributo ser um atributo relacionado a tempo, O JPA precisa que eu sinalizer isso por anota��o @temporal
	@DateTimeFormat(pattern = "dd/MM/yyyy")//formato padr�o da minha data. Especifiquei que ser� dia/mes/ano
	private Date dataCriacao;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	/*Relacionamento muitos pra um. Muitas musicas pra um album. Many se refere a classe to One se refere ao atributo
	 * ManyToOne = ClasseToAtributo
	 * FecthType Eager= Quando selecionar a musica, o album todo ser� carregado. Lazy - O album n�o carrega junto, carregamento � feito por demanda
	*/
	@JoinColumn(name="alb_id")/*A minha foreing key, A coluna da outra tabela que far� referencia esta tabela ser� a coluna alb_id.
	 Cada linha dessa minha tabela estar� ligada a essa foreing key
	*/
	@JsonManagedReference
	private Album album;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
}

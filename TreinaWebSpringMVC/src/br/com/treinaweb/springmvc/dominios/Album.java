package br.com.treinaweb.springmvc.dominios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity //Esta classe é uma entidade que pode ser persistida em BD
@Table(name = "alb_albuns")//O nome da tabela referente a essa entity é "alb_albuns"
//Classe Pojo. Uma Classe que possui suas variáveis de forma privada e métodos getters e setters públicos.
public class Album {

	@Id //Estou informando que essa variável será meu id
	@Column(name="alb_id")//Essa variável será uma coluna no meu banco e terá o nome descrito em "name"
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Ovalor dessa variável será gerada automaticamente
	private Long id;
	
	@NotNull(message=" Voce deve preencher esse campo")
	@NotEmpty(message=" Voce deve preencher esse campo")
	@Column(name="alb_nome", length = 50, nullable = false) // largura (length) máximo de caracteres = 50, não pode ser nulo(nullable = false)
	private String nome;
	
	@NotNull(message="Voce deve preencher esse campo")
	@Min(value=1990, message="O ano de lançamento deve ser maior que 1990")
	@Max(value=2020, message= "O ano de lançamento deve ser menor que 2020")
	@Column(name="alb_ano_lancamento", length = 4, nullable = false)
	private int anoDeLancamento;

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

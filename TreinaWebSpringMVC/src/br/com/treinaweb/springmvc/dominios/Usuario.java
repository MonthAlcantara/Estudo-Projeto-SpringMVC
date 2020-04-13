package br.com.treinaweb.springmvc.dominios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="usr_usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O nome de usuário é obrigatório")
	@Length(min = 3, max=10, message = "O numero de caracteres deve ser maior que 3 e menor que 10")
	@Column(name = "usr_username",length = 10, unique = true, nullable = false)
	private String username;
	
	
	@Column(name = "usr_password", nullable = false, length = 150)
	private String password;
	@Column(name = "usr_roler",length = 20, nullable = false)
	private String roler;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoler() {
		return roler;
	}
	public void setRoler(String roler) {
		this.roler = roler;
	}
	
	
}

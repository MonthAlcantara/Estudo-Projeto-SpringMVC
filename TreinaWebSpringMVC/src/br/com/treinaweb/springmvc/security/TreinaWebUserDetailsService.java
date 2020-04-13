package br.com.treinaweb.springmvc.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.treinaweb.springmvc.dominios.Usuario;
import br.com.treinaweb.springmvc.repositorios.RepositorioUsuario;
//Vai Auxiliar o spring Security a ter acesso aos dados do usuario 
public class TreinaWebUserDetailsService  implements UserDetailsService{
	
	@Autowired
	private RepositorioUsuario repositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//Esse método lança uma excessão
		Usuario usuario = repositorio.findByUsername(username);
		if(usuario == null) { // Caso o usuario seja nulo, não seja encontrado no banco, ele vai me retornar a excessão esperada pelo método
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		Set<GrantedAuthority> perfis = new HashSet<GrantedAuthority>(); //Classe do proprio Spring. Ela auxilia a identificar o perfil do usuario 
		perfis.add(new SimpleGrantedAuthority(usuario.getRole())); // Estou criando um SimpleGrantedAuthority baseado na role que o usuario pesquisado possui
		return new User(usuario.getUsername(), usuario.getPassword(), perfis);
	} 

}

package br.com.treinaweb.springmvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.treinaweb.springmvc.dominios.Usuario;
import br.com.treinaweb.springmvc.repositorios.RepositorioUsuario;
@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Usuario> usuarios = repositorioUsuario.findAll();
		model.addAttribute("usuarios", usuarios);
		return "usuario.listar.tiles";
	}

	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String adicionar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario.adicionar.tiles";
	}
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String adicionar(Model model,@ModelAttribute("usuario")@Valid Usuario usuario, BindingResult resultado) {
		if(resultado.hasErrors()) {
						return "usuario.adicionar.tiles";	
		}else {
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//Classe do Spring Security que codifica senha
			usuario.setPassword(encoder.encode(usuario.getPassword()));//Pegando a senha do usuario e salvando dr forma codificada
			repositorioUsuario.save(usuario);//Depois de codificar a senha do usuario, eu salvo no meu BD	
		}
		
		return "redirect:/usuarios/listar";
	}

	
}

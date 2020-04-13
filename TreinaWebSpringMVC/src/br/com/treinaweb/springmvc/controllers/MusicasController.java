package br.com.treinaweb.springmvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.treinaweb.springmvc.dominios.Musica;
import br.com.treinaweb.springmvc.repositorios.RepositorioAlbum;
import br.com.treinaweb.springmvc.repositorios.RepositorioMusica;

@Controller
@RequestMapping("/musicas")
public class MusicasController {
	@Autowired // Objetos serão criados pelo Spring em tempo de execução e serão injetados por
				// ele
	private RepositorioAlbum repositorioAlbum;
	@Autowired
	private RepositorioMusica repositorioMusica;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Musica> musicas = repositorioMusica.findAll();
		model.addAttribute("musicas", musicas);
		return "musica.listar.tiles";
	}

	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String adicionar(Model model) {
		model.addAttribute("musica", new Musica());
		model.addAttribute("albuns", repositorioAlbum.findAll());// Além da musica vazia, eu devo passar também uma
																	// lista de albuns para o usuario escollher em que
																	// album ele quer salvar a musica
		return "musica.adicionar.tiles";
	}

	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String adicionar(Model model, @ModelAttribute("musica") /*
																	 * Pegando o model atributte e setado ele em nova
																	 * musica
																	 */ @Valid Musica novaMusica,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("musicas", repositorioMusica.findAll());
			return "musica.adicionar.tiles";
		} else {
			repositorioMusica.save(novaMusica);
			return "redirect:/musicas/listar";
		}
	}

	@RequestMapping(value = "/alterar/{id}", method = RequestMethod.GET)
	public String alterar(Model model, @PathVariable("id") Long id) {
		Musica musicaAlterar = repositorioMusica.findOne(id);
		model.addAttribute("musica", musicaAlterar);
		model.addAttribute("albuns", repositorioAlbum.findAll());
		return "musica.alterar.tiles";
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public String alterar(Model model, @ModelAttribute("musica") @Valid Musica novaMusica, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("albuns", repositorioAlbum.findAll());
			return "musica.alterar.tiles";
		} else {
			repositorioMusica.save(novaMusica);
			return "redirect:/musicas/listar";
		}
	}

	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public String excluir(@PathVariable Long id) {
		Musica musica = repositorioMusica.findOne(id); // Pesquisa a musica do id e joga no objeto
		musica.setAlbum(null); // Setando null ao albuma da musica. Como setei a musica na entidade Album como
								// orphanRemoval, eu vou poder apagar a musica
		repositorioMusica.delete(musica);// Como ela está orfã de album, posso deletar a musica. A referencia entre
											// album e musica está null.
		return "redirect:/musicas/listar";

	}

	@RequestMapping(value = "/porNome", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Musica> pesquisaPorNome(@RequestParam(name = "nome", defaultValue = "") String nome) {
		return repositorioMusica.findByNome(nome);
	}
}
 
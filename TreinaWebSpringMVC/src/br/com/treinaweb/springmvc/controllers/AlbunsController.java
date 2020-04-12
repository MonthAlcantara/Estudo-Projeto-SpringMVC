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

import br.com.treinaweb.springmvc.dominios.Album;
import br.com.treinaweb.springmvc.repositorios.RepositorioAlbum;

@Controller
@RequestMapping("/albuns")
public class AlbunsController {

	// Inverção de Controle. O Spring é quem vai instanciar e gerenciar esse objeto.
	@Autowired
	private RepositorioAlbum repositorio;
	/* action que irá listar os albuns existentes */

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Album> albuns = repositorio.findAll();
		model.addAttribute("albuns", albuns);
		return "album.listar.tiles";
	}
	/* Action que irá me entregar a página de adição quando solicitada via URL */

	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String adicionar(Model model) {
//		Quando ele chamar a action pela url, ele vai criar uma instancia de album vazia.
		model.addAttribute("album", new Album());// Este album é uma instancia que vai ser usada pelo form
		return "album.adicionar.tiles";
	}

	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String adicionar(@ModelAttribute("album" /* modelAtribute declarado no form */) @Valid Album novoAlbum,
			BindingResult result, Model model) {
		if (result.hasErrors()) {// se houver alguma coisa que viole as annotations de validação ele retornará
									// para a página de adição
			return "album.adicionar.tiles";
		}
		/*
		 * Caso esteja tudo dentro das regras de validação com os dados informados pelo
		 * usuário, ele seguirá e o spring irá injetar os dados do form no objeto album
		 * que ele mesmo irá criar o que permitirá isso é o path que colocamos no input.
		 * O path linka o input com as variaveis de album
		 */
		repositorio.save(novoAlbum);
		return "redirect:/albuns/listar";// O retorno dessa action é o encaminhamento para a action listar deste mesmo
											// Controller /albuns
	}

	/*
	 * Buscar por id para alteração. o id vem pela rota, recupera o dono do id e
	 * joga os valores encontrados no input da pagina alterar
	 */
	@RequestMapping(value = "/alterar/{id}")
	public String alterar(Model model, @PathVariable("id") Long id) {
		Album AlbumASerAlterado = repositorio.findOne(id);// findOne busca pela primaryKey. O tipo é especificado na
															// Interfce do repositório
		model.addAttribute("album", AlbumASerAlterado);
		return "album.alterar.tiles";
	}

	/* Altera os dados do album */
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public String alterar(Model model, @ModelAttribute("albun") @Valid Album album, BindingResult result) {
		if (result.hasErrors()) {
			return "album.alterar.tiles";
		}
		repositorio.save(album);/*
								 * Esse save serve para salvar ou atualizar caso ja exista. O próprio método faz
								 * isso internamente
								 */
		return "redirect:/albuns/listar";

	}

	/* Removendo album da lista */
	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public String excluir(Model model, @PathVariable("id") Long id) {
		repositorio.delete(id);
		return "redirect:/albuns/listar";
	}

	@RequestMapping(value = "/porNome", method = RequestMethod.GET, produces = "application/json") 
	// No produces eu estou dizendo que minha action retornará um Json.
//Ou seja ele irá transformar o meu objeto Album num Json e retornará na verdade, um Json para quem fizer a requisição
	public @ResponseBody List<Album> pesquisaPorNome(@RequestParam(name="nome", defaultValue="") String nome) {
		return repositorio.findByNome(nome);// Metodo criado pelo SpringData por eu ter colocado a declaração "findByNome" na Classe Album.
	}
}

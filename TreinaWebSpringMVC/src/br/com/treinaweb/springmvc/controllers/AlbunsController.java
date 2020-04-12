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

	// Inver��o de Controle. O Spring � quem vai instanciar e gerenciar esse objeto.
	@Autowired
	private RepositorioAlbum repositorio;
	/* action que ir� listar os albuns existentes */

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Album> albuns = repositorio.findAll();
		model.addAttribute("albuns", albuns);
		return "album.listar.tiles";
	}
	/* Action que ir� me entregar a p�gina de adi��o quando solicitada via URL */

	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String adicionar(Model model) {
//		Quando ele chamar a action pela url, ele vai criar uma instancia de album vazia.
		model.addAttribute("album", new Album());// Este album � uma instancia que vai ser usada pelo form
		return "album.adicionar.tiles";
	}

	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String adicionar(@ModelAttribute("album" /* modelAtribute declarado no form */) @Valid Album novoAlbum,
			BindingResult result, Model model) {
		if (result.hasErrors()) {// se houver alguma coisa que viole as annotations de valida��o ele retornar�
									// para a p�gina de adi��o
			return "album.adicionar.tiles";
		}
		/*
		 * Caso esteja tudo dentro das regras de valida��o com os dados informados pelo
		 * usu�rio, ele seguir� e o spring ir� injetar os dados do form no objeto album
		 * que ele mesmo ir� criar o que permitir� isso � o path que colocamos no input.
		 * O path linka o input com as variaveis de album
		 */
		repositorio.save(novoAlbum);
		return "redirect:/albuns/listar";// O retorno dessa action � o encaminhamento para a action listar deste mesmo
											// Controller /albuns
	}

	/*
	 * Buscar por id para altera��o. o id vem pela rota, recupera o dono do id e
	 * joga os valores encontrados no input da pagina alterar
	 */
	@RequestMapping(value = "/alterar/{id}")
	public String alterar(Model model, @PathVariable("id") Long id) {
		Album AlbumASerAlterado = repositorio.findOne(id);// findOne busca pela primaryKey. O tipo � especificado na
															// Interfce do reposit�rio
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
								 * Esse save serve para salvar ou atualizar caso ja exista. O pr�prio m�todo faz
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
	// No produces eu estou dizendo que minha action retornar� um Json.
//Ou seja ele ir� transformar o meu objeto Album num Json e retornar� na verdade, um Json para quem fizer a requisi��o
	public @ResponseBody List<Album> pesquisaPorNome(@RequestParam(name="nome", defaultValue="") String nome) {
		return repositorio.findByNome(nome);// Metodo criado pelo SpringData por eu ter colocado a declara��o "findByNome" na Classe Album.
	}
}

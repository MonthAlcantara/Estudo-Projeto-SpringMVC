package br.com.treinaweb.springmvc.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller 
@RequestMapping("/hello")
public class HelloController  {
	
	
	@RequestMapping("/message")
	public String message() {
		return "hello";
//		 O viewResolver declarado no xml vai interceptar essa resposta e entender que essa 
//		Sting na verdade � o nome de uma p�gina jsp a qual ela vai procurar na pasta programada no xml
//		 e ir� enviar para o Browser
	}
@RequestMapping("/mensagemDoServidor")	
public String mensagemDoServidor(Model model) {//esse Objeto model � injetado pelo Spring
	model.addAttribute("mensagem",new Date().toString());
		return "mensagemDoServidor";//Pagina jsp que vai ser enviada para o ususario
//Nesse tipo de metodo eu posso passar varios atributos para a minha view sempre adicionando atributo
//O model � uma interface
}
@RequestMapping("/mensagemDoServidor2")
public ModelAndView mensagemDoServidorV2() {
	return new ModelAndView("mensagemDoServidor","mensagem", new Date().toString());
	//Neste modelo eu consigo passar apenas um objeto para a minha view
	//ModelAndView � uma Classe
}

//Caminho inverso, do Browser para o programa
@RequestMapping("/receberMensagem/{mensagem}")//O usuario tem que passar o url inteiro + a mensagem, sen�o dar� 404
public String receberMensagem(Model model, @PathVariable("mensagem") String msg) {
	//A anota��o serve para pegar o conteudo de "mensagem" e jogar em msg. Nesse caso o usuario � obrigado a passar a mensagem sen�o dar� o erro 400 (Bad Request)
model.addAttribute("mensagem",msg);
return "mensagemDoServidor";
}
@RequestMapping("/receberMensagem2")//O usuario tem que passar o url inteiro + a mensagem, sen�o dar� 404
public String receberMensagemV2(Model model, @RequestParam(value = "mensagem", required = false, defaultValue = "Valor default") String msg) {
	//A anota��o serve para pegar o conteudo de "mensagem", que ser� passada nesse caso por QueryString. required = false informa que 
	//n�o � obrigat�rio passar esse valor e defaultValue = "seta um valor inicial ao parametro"
model.addAttribute("mensagem",msg);
return "mensagemDoServidor";
}
}

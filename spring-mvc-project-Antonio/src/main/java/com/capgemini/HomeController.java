package com.capgemini;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.modelo.User;

//vamos a anotarla como un contrlador
@Controller
public class HomeController {

//	vamos a crear un metodo publico que reciba las peticiones
//	este metodo va a devolver un modelandview

//	indicamos la url necesaria para llegar a este metodo
	@RequestMapping(value = "/hola")
	public ModelAndView saludo() {

		ModelAndView mv = new ModelAndView();

		// añadimos los recursos a la vista
		mv.addObject("titulo", "Bienvenido a Spring MVC (Al fin...)"); // parejas clave - valor
		mv.addObject("mensaje", "Arrancando mi primera aplicación Spring MVC");

		// indicamos la vista que queremos abrir
		mv.setViewName("saludo"); // saludo.jsp - debemos tener una pagina jsp

		return mv;

	}

//	especificamos a que tipo de peticiones HTTP responde, se podria utilizar getmapping
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String home(Locale locale, Model model) {

//		vamos hacer una home que nos devuelva la fecha

		Date date = new Date();
//		la formateamos
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "pagina";

	}

//	vamos a definir un metodo que recibe una peticion de tipo post
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(User user, Model model) {

		model.addAttribute("userName", user.getUserName());

		return "user";
	}
	


}

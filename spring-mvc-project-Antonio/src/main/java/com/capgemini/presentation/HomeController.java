package com.capgemini.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {


	@RequestMapping(value = "/")
	public ModelAndView login() {

		ModelAndView mv = new ModelAndView();
		
		//a rellenar

		return mv;

	}

}

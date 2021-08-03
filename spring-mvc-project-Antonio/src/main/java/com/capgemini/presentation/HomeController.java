package com.capgemini.presentation;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.bussines.servicio.ServicioUser;
import com.capgemini.bussines.servicioImpl.ServicioUserImpl;
import com.capgemini.modelo.CategoryVO;
import com.capgemini.modelo.TaskVO;
import com.capgemini.modelo.UserVO;
import com.capgemini.modelo.UserVO.UserStatus;


@Controller
public class HomeController {
	
	ServicioUser su = new ServicioUserImpl();
	



	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio(Model modelo) {
		
		su.insertar(new UserVO("sr_capgemini@capgemini.com", true, "master_of_the_universe", "12345", UserStatus.ENABLED,
				new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));
		su.insertar(new UserVO("adam_responde@capgemini.com", false, "centralita123", "adam_the_best", UserStatus.DISABLED,
				new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));
		
		modelo.addAttribute("usuario", su.findAll());
		return "ppal";

	}

}

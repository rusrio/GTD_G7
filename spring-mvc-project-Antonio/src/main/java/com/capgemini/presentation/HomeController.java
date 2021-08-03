package com.capgemini.presentation;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bussines.servicio.ServicioUser;
import com.capgemini.bussines.servicioImpl.ServicioUserImpl;
import com.capgemini.modelo.CategoryVO;
import com.capgemini.modelo.TaskVO;
import com.capgemini.modelo.UserVO;
import com.capgemini.modelo.UserVO.UserStatus;

@Controller
public class HomeController {

	ServicioUser su = new ServicioUserImpl();

	@GetMapping("/")
	public String login() {
		
		if (su.findAll().size() != 4) {//ELIMINAR, previo para añadir datos
			su.insertar(new UserVO("sr_capgemini@capgemini.com", true, "root", "root",
					UserStatus.ENABLED, new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));
			su.insertar(new UserVO("adam_responde@capgemini.com", false, "normal", "normal",
					UserStatus.DISABLED, new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));
		}
		
		return "login";
	}

	@PostMapping("/validaUser")
	public String validaUser(@RequestParam String login, @RequestParam String pwd, Model modelo) {
		
		UserVO user = su.findByLogin(login);//creamos el usuario buscandolo por el login
		
		if(user==null) {//si no podemos encontrar el usuario, es que el login está mal
			return "login";
		}

		if (user.getPassword().equals(pwd)) {//comprobamos que, exisitendo el login, el pwd es correcto
			if (user.isAdmin()) {//si es admin, vamos a su pagina
				return "redirect:ppal";
			} else {//si es un user normal, a la que le corresponda
				return "user";
			}
		}else {//si la pwd no es correcta, volvemos a pedir que ingrese credenciales
			return "login";
		}
	}

	@GetMapping("/ppal")
	public String ppal(Model modelo) {
		
		modelo.addAttribute("usuario", su.findAll());

		return "ppal";

	}

}

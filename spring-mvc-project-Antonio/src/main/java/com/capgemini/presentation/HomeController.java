package com.capgemini.presentation;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	// creamos un metodo para que nos cree al principio el usuario admin y algun
	// otro usuario

	public void inicio() {

		if (su.findById(1) == null) {
			su.insertar(new UserVO("root@capgemini.com", true, "root", "root", UserStatus.ENABLED,
					new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));

			su.insertar(new UserVO("usuario1@capgemini.com", false, "usuario1", "usuario1", UserStatus.ENABLED,
					new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));

			su.insertar(new UserVO("usuario2@capgemini.com", false, "usuario2", "usuario2", UserStatus.ENABLED,
					new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));

			su.insertar(new UserVO("usuario3@capgemini.com", false, "usuario3", "usuario3", UserStatus.DISABLED,
					new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));

			su.insertar(new UserVO("usuario4@capgemini.com", false, "usuario4", "usuario4", UserStatus.DISABLED,
					new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));
		}
	}

	@GetMapping("/")
	public String login() {

		inicio();

		return "login";
	}

	@PostMapping("/validaUser")
	public String validaUser(@RequestParam String login, @RequestParam String pwd, Model modelo) {

		UserVO user = su.findByLogin(login);// creamos el usuario buscandolo por el login

		if (user == null) {// si no podemos encontrar el usuario, es que el login está mal
			return "login";
		}

		if (user.getPassword().equals(pwd)) {// comprobamos que, exisitendo el login, el pwd es correcto
			if (user.isAdmin()) {// si es admin, vamos a su pagina
				return "redirect:admin";
			} else {// si es un user normal, a la que le corresponda
				if (user.getStatus().equals(UserStatus.ENABLED)) {//si esta habilidado puede entrar
					return "user";
				} else {//si no, no puede
					return "login";
				}
			}
		} else {// si la pwd no es correcta, volvemos a pedir que ingrese credenciales
			return "login";
		}
	}

	@GetMapping("/admin")
	public String admin(Model modelo) {

		modelo.addAttribute("usuario", su.findAll());

		return "admin";

	}

	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam int iduser) {
		su.eliminar(su.findById(iduser));
		return "redirect:admin";
	}

	@GetMapping("/activeuser")
	public String activeUser(@RequestParam int iduser) {
		UserVO user = su.findById(iduser);
		if (user.getStatus() == UserStatus.DISABLED) {
			user.setStatus(UserStatus.ENABLED);
		} else {
			user.setStatus(UserStatus.DISABLED);
		}

		return "redirect:admin";
	}
	
	@GetMapping("/insertauser")
	public String insertaUser(Model modelo) {
		modelo.addAttribute("usuario",new UserVO());
		return "registro";
	}
	
	@PostMapping("/submituser")
	public String submiUser(@ModelAttribute UserVO user,Model modelo) {
		user.setAdmin(false);
		user.setStatus(UserStatus.ENABLED);
		user.setCategorias(new ArrayList<CategoryVO>());
		user.setTareas(new ArrayList<TaskVO>());
		su.insertar(user);
		return "redirect:/";
		
	}

}

package com.capgemini.presentation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bussines.servicio.ServicioCategory;
import com.capgemini.bussines.servicio.ServicioUser;
import com.capgemini.bussines.servicioImpl.ServicioCategoryImpl;
import com.capgemini.bussines.servicioImpl.ServicioUserImpl;
import com.capgemini.modelo.CategoryVO;
import com.capgemini.modelo.TaskVO;
import com.capgemini.modelo.UserVO;
import com.capgemini.modelo.UserVO.UserStatus;

@Controller
public class HomeController {

	ServicioUser su = new ServicioUserImpl();
	ServicioCategory sc = new ServicioCategoryImpl();

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

			sc.insertar(new CategoryVO("categoria1", su.findById(2), new ArrayList<TaskVO>()));
			sc.insertar(new CategoryVO("categoria2", su.findById(2), new ArrayList<TaskVO>()));
			sc.insertar(new CategoryVO("categoria3", su.findById(2), new ArrayList<TaskVO>()));
			sc.insertar(new CategoryVO("categoria4", su.findById(2), new ArrayList<TaskVO>()));
			sc.insertar(new CategoryVO("categoria5", su.findById(2), new ArrayList<TaskVO>()));
			sc.insertar(new CategoryVO("categoria5", su.findById(2), new ArrayList<TaskVO>()));

		}
	}

	@GetMapping("/")
	public String login() {

		inicio();

		return "login";
	}

	@PostMapping("/validaUser")
	public String validaUser(@RequestParam String login, @RequestParam String pwd, Model modelo) {

		String mensaje;
		UserVO user = su.findByLogin(login);// creamos el usuario buscandolo por el login

		if (user == null) {// si no podemos encontrar el usuario, es que el login está mal
			mensaje = "No se ha encontrado el usuario en la base de datos";
			modelo.addAttribute("mensaje", mensaje);
			return "login";
		}

		if (user.getPassword().equals(pwd)) {// comprobamos que, exisitendo el login, el pwd es correcto
			if (user.isAdmin()) {// si es admin, vamos a su pagina
				return "redirect:admin?login=" + login;
			} else {// si es un user normal, a la que le corresponda
				if (user.getStatus().equals(UserStatus.ENABLED)) {// si esta habilidado puede entrar
					return "redirect:user?login=" + login;
				} else {// si no, no puede
					mensaje = "Usuario no habilitado, pongase en contacto con su administrador";
					modelo.addAttribute("mensaje", mensaje);
					return "login";
				}
			}
		} else {// si la pwd no es correcta, volvemos a pedir que ingrese credenciales
			mensaje = "La contraseña no es correcta";
			modelo.addAttribute("mensaje", mensaje);
			return "login";
		}
	}

	/**
	 * Para mostranos el home si nos logueamos como admin
	 */
	@GetMapping("/admin")
	public String admin(@RequestParam String login, Model modelo) {

		List<UserVO> usuarios = su.findAll();
		usuarios.remove(su.findByLogin(login));// para quitar el admin de la lista
		modelo.addAttribute("usuarios", usuarios);
		modelo.addAttribute("admin", su.findByLogin(login));// pasamos el objeto para modificar sus datos luego
		return "admin";

	}

	/**
	 * Para mostranos el home si nos logueamos como user normal
	 */
	@GetMapping("/user")
	public String user(@RequestParam String login, Model modelo) {
		UserVO user = su.findByLogin(login);
		modelo.addAttribute("user", user);// pasamos el objeto para modificar sus datos luego
		return "user";
	}

	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam String login, @RequestParam int iduser) {
		su.eliminar(su.findById(iduser));
		return "redirect:admin?login=" + login;
	}

	@GetMapping("/activeuser")
	public String activeUser(@RequestParam String login, @RequestParam int iduser) {
		UserVO user = su.findById(iduser);
		if (user.getStatus() == UserStatus.DISABLED) {
			user.setStatus(UserStatus.ENABLED);
		} else {
			user.setStatus(UserStatus.DISABLED);
		}

		return "redirect:admin?login=" + login;
	}

	@GetMapping("/insertauser")
	public String insertaUser(Model modelo) {
		modelo.addAttribute("user", new UserVO());
		return "registro";
	}

	@PostMapping("/submituser")
	public String submitUser(@ModelAttribute UserVO user, Model modelo) {

		String mensaje;

		user.setAdmin(false);
		user.setStatus(UserStatus.ENABLED);
		user.setCategorias(new ArrayList<CategoryVO>());
		user.setTareas(new ArrayList<TaskVO>());

		su.insertar(user);
		return "redirect:/";

	}

	@GetMapping("/modificadatos")
	public String modificaDatos(@RequestParam int iduser, Model modelo) {
		modelo.addAttribute("user", su.findById(iduser));
		return "modificadatos";
	}

	@PostMapping("/modificauser")
	public String modificaUser(@ModelAttribute UserVO user, Model modelo) {

		UserVO user_mod = su.findById(user.getIduser());

		user_mod.setLogin(user.getLogin());
		user_mod.setPassword(user.getPassword());
		user_mod.setEmail(user.getEmail());

		su.modificar(user_mod);

		return "redirect:user?login=" + user_mod.getLogin();

	}

	/**
	 * Métodos para trabajar con las categorias
	 * 
	 */

	@GetMapping("/listacategorias")
	public String listaCategorias(@RequestParam int iduser, Model modelo) {
		UserVO user = su.findById(iduser);
		List<CategoryVO> categorias = user.getCategorias();
		modelo.addAttribute("user", user);
		modelo.addAttribute("categorias", categorias);
		return "listacategorias";
	}

	@GetMapping("/deletecategory")
	public String deleteCategory(@RequestParam int iduser, @RequestParam int idcategory) {
		sc.eliminar(sc.findById(idcategory));
		return "redirect:listacategorias?iduser=" + iduser;
	}

	@GetMapping("/insertacategoria")
	public String insertaCategoria(int iduser, Model modelo) {
		modelo.addAttribute("categoria", new CategoryVO());
		modelo.addAttribute("user", su.findById(iduser));
		return "registrocategoria";
	}

	@PostMapping("/submitcategoria")
	public String submitCategoria(@ModelAttribute CategoryVO category, int iduser, Model modelo) {

		category.setUser(su.findById(iduser));
		category.setTareas(new ArrayList<TaskVO>());
		sc.insertar(category);

		return "redirect:listacategorias?iduser=" + iduser;

	}

	@GetMapping("/modificacategoria")
	public String modificaCategory(@RequestParam int idcategory, Model modelo) {
		modelo.addAttribute("category", sc.findById(idcategory));
		modelo.addAttribute("user", sc.findById(idcategory).getUser());
		return "modificacategoria";
	}

	@PostMapping("/modifycategory")
	public String modifyCategory(@ModelAttribute CategoryVO category, Model modelo) {

		CategoryVO cat_mod = sc.findById(category.getIdcategory());

		cat_mod.setName(category.getName());

		sc.modificar(cat_mod);

		return "redirect:listacategorias?iduser=" + cat_mod.getUser().getIduser();

	}

}

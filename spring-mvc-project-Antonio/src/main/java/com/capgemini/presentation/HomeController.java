package com.capgemini.presentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bussines.servicio.ServicioCategory;
import com.capgemini.bussines.servicio.ServicioTask;
import com.capgemini.bussines.servicio.ServicioUser;
import com.capgemini.bussines.servicioImpl.ServicioCategoryImpl;
import com.capgemini.bussines.servicioImpl.ServicioTaskImpl;
import com.capgemini.bussines.servicioImpl.ServicioUserImpl;
import com.capgemini.modelo.CategoryVO;
import com.capgemini.modelo.TaskVO;
import com.capgemini.modelo.UserVO;
import com.capgemini.modelo.UserVO.UserStatus;

@Controller
public class HomeController {

	ServicioUser su = new ServicioUserImpl();
	ServicioCategory sc = new ServicioCategoryImpl();
	ServicioTask st = new ServicioTaskImpl();

	/**
	 * Este método se encarga de iniciar la BBDD con algunos datos, para que podamos
	 * trabajar con la app. Posteriormente podremos borrar los datos aquí
	 * introducidos Solamente se ejecuta una vez, si el usuario admin no esta
	 * creado, gracias al condicional if (su.findById(1) == null)
	 */
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

	/**
	 * Con este controlador, mostramos la página de login cuando accedemos al
	 * server. Desde aquí se ejecutaría el método inicio() (arriba explicado)
	 */
	@GetMapping("/")
	public String login() {

		inicio();

		return "login";
	}

	/**
	 * Con este controlador, validamos al usuario introducido en la página de login
	 * Buscamos un usuario gracias al login introducido y lo almacenamos
	 * 
	 * En caso de que el usuario no exista, volvemos a mostrar la pagina de login
	 * con un mensaje indincando esta situacion
	 * 
	 * Si el usuario existe, comprobamos que su pwd es correcta, si no, devolvemos
	 * la pagina de login con un mensaje indincando esta situacion
	 * 
	 * Si user existe y pwd es correcta, comprobamos si es el admin, y lo
	 * redirigimos a su home page
	 * 
	 * Si no es el admin, comprobamos si está habilitado o no HABILITADO =
	 * devolvemos su home page DESAHBILITADO = devolvemos la pagina de login con un
	 * mensaje indincando esta situacion
	 */
	@PostMapping("/validauser")
	public String validaUser(@RequestParam String login, @RequestParam String pwd, Model modelo) {

		String mensaje;
		UserVO user = su.findByLogin(login);

		if (user == null) {
			mensaje = "No se ha encontrado el usuario en la base de datos";
			modelo.addAttribute("mensaje", mensaje);
			return "login";
		}

		if (user.getPassword().equals(pwd)) {
			if (user.isAdmin()) {
				return "redirect:admin?login=" + login;
			} else {
				if (user.getStatus().equals(UserStatus.ENABLED)) {
					return "redirect:user?login=" + login;
				} else {
					mensaje = "Usuario no habilitado, pongase en contacto con su administrador";
					modelo.addAttribute("mensaje", mensaje);
					return "login";
				}
			}
		} else {
			mensaje = "La contraseña no es correcta";
			modelo.addAttribute("mensaje", mensaje);
			return "login";
		}
	}

	/**
	 * Con este controlador mostramos el home si nos logueamos como admin Desde aqui
	 * podremos ver una lista con todos los usuario (quitamos el admin)
	 *
	 * Pasamos una lista con los usuarios para gestionarlos y el mismo admin, para
	 * poder modificar sus datos personales
	 */
	@GetMapping("/admin")
	public String admin(@RequestParam String login, Model modelo) {

		List<UserVO> usuarios = su.findAll();
		usuarios.remove(su.findByLogin(login));
		modelo.addAttribute("usuarios", usuarios);
		modelo.addAttribute("admin", su.findByLogin(login));
		return "admin";

	}

	/**
	 * Con este controlador mostramos el home si nos logueamos como user normal
	 * 
	 * Pasamos el usuario para poder modificar sus datos personales
	 */
	@GetMapping("/user")
	public String user(@RequestParam String login, Model modelo) {
		UserVO user = su.findByLogin(login);
		modelo.addAttribute("user", user);
		return "user";
	}

	/**
	 * Con este controlador eliminamos un usuario, y redirigimos al home del
	 * administrador
	 * 
	 * Solamente se puede acceder a este método desde el home del admin
	 */
	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam String login, @RequestParam int iduser) {
		su.eliminar(su.findById(iduser));
		return "redirect:admin?login=" + login;
	}

	/**
	 * Con este controlador habilitamos/desahabilitamos un usuario, y redirigimos al
	 * home del administrador
	 * 
	 * Solamente se puede acceder a este método desde el home del admin
	 */
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

	/**
	 * Con este controlador, devolvemos la vista para el registro de un nuevo
	 * usuario pasándole un objeto UserVO, que será el que posteriormente
	 * persisteremos en la BBDD
	 */
	@GetMapping("/insertauser")
	public String insertaUser(Model modelo) {
		modelo.addAttribute("user", new UserVO());
		return "registro";
	}

	/**
	 * Con este controlador, añadimos un nuevo usuario a nuestra BBDD
	 * 
	 * Primero comprobamos que no haya un usuario ya con ese login, puesto que el
	 * login es unico y saltaría una excepción al intentar persistirlo en BBDD
	 * Volvemos a devolver la página de registro con un mensaje indicandolo en caso
	 * de que asi sea
	 * 
	 * Si los datos son correctos, hacemos setters a los valores por defecto que
	 * tendrá el usuario, y persistimos
	 * 
	 * También añadimos la categoria especial inbox, asociada a ese usuario
	 * 
	 * Devolvmos la página de login con un mensaje para que prueba sus nuevas
	 * credenciales
	 */
	@PostMapping("/submituser")
	public String submitUser(@ModelAttribute UserVO user, Model modelo) {

		String mensaje;

		if (!(su.findByLogin(user.getLogin()) == null)) {
			mensaje = "Ya existe un usuario con ese login";
			modelo.addAttribute("mensaje", mensaje);
			modelo.addAttribute("user", new UserVO());
			return "registro";
		}
		

		if (!(user.getPassword().equals(user.getPassword2()))) {
			mensaje = "Introduce la misma contraseña dos veces, por favor";
			modelo.addAttribute("mensaje", mensaje);
			modelo.addAttribute("user", new UserVO());
			return "registro";
		}

		user.setAdmin(false);
		user.setStatus(UserStatus.ENABLED);
		user.setCategorias(new ArrayList<CategoryVO>());
		user.setTareas(new ArrayList<TaskVO>());

		su.insertar(user);
		
		CategoryVO category = new CategoryVO();
		category.setName("INBOX");
		category.setUser(user);
		category.setTareas(new ArrayList<TaskVO>());
		
		sc.insertar(category);

		mensaje = "Prueba tus nuevas credenciales";
		modelo.addAttribute("mensaje", mensaje);

		return "login";

	}

	/**
	 * Con este controlador, devolvemos la vista para modificar un usuario pasándole
	 * el id del objeto UserVO a modificar
	 */
	@GetMapping("/modificadatos")
	public String modificaDatos(@RequestParam int iduser, Model modelo) {
		modelo.addAttribute("user", su.findById(iduser));
		return "modificadatos";
	}

	/**
	 * Con este controlador, modificamos un usuario en nuestra BBDD
	 * 
	 * Lo pasamos como parámetro y lo buscamos en la BBDD, a ese usuario buscado, le
	 * actualizamos los valores que hemos cambiado, y lo modificamos en BBDD
	 */
	@PostMapping("/modificauser")
	public String modificaUser(@ModelAttribute UserVO user, Model modelo) {

		UserVO user_mod = su.findById(user.getIduser());

		user_mod.setLogin(user.getLogin());
		user_mod.setPassword(user.getPassword());
		user_mod.setEmail(user.getEmail());

		su.modificar(user_mod);

		return "redirect:user?login=" + user_mod.getLogin();

	}
	
	@PostMapping("/submittask")
	public String submitTask(@ModelAttribute TaskVO task, @RequestParam String login, Model modelo) {
		//Para insertar la tarea se pasará el login del usuario para mostrarlo a través de un input hidden en la vista html.
		UserVO user = su.findByLogin(login);
		task.setUser(user); // Seteamos al usuario de la tarea al usuario logeado.
		task.setCreated(LocalDate.now()); // Seteamos la fecha de la tarea a la proporcionada por LocalDate.now();.
		st.insertar(task);
		return "user?login="+login;

	}
	
	@GetMapping("/insertarTask")
	public String insertaTask(Model modelo, @RequestParam String login) {
		TaskVO tarea = new TaskVO();
		UserVO user = new UserVO();
		UserVO usuariologeado = su.findByLogin(login);
		modelo.addAttribute("task", tarea);
		modelo.addAttribute("user", usuariologeado);
		return "insertartask";
	}

	/**
	 * Con este controlador, listamos las categorias de cada usuario Se accede desde
	 * la propia pagina del usuario, y le pasamos su id como parametro, la cual
	 * usaremos para buscarlo en BBDD y obtener la lista con sus categorias
	 * asociadas
	 * 
	 * Las añadimos al modelo, asi como el objeto usuario que nos ha llamado al
	 * controlador
	 */
	@GetMapping("/listacategorias")
	public String listaCategorias(@RequestParam int iduser, Model modelo) {
		UserVO user = su.findById(iduser);
		List<CategoryVO> categorias = user.getCategorias();
		modelo.addAttribute("user", user);
		modelo.addAttribute("categorias", categorias);
		return "listacategorias";
	}

	/**
	 * Con este controlador eliminamos una categoria, y redirigimos a la vista de
	 * las categorias del usuario al que pertenecen
	 * 
	 * Un usuario solamente puede eliminar sus categorias
	 */
	@GetMapping("/deletecategory")
	public String deleteCategory(@RequestParam int iduser, @RequestParam int idcategory) {
		sc.eliminar(sc.findById(idcategory));
		return "redirect:listacategorias?iduser=" + iduser;
	}

	/**
	 * Con este controlador, devolvemos la vista para el registro de una nueva
	 * categoria pasándole un objeto CategoryVO, que será el que posteriormente
	 * persisteremos en la BBDD y un objeto UserVO, que sera el propietario de la
	 * categoria
	 */
	@GetMapping("/insertacategoria")
	public String insertaCategoria(int iduser, Model modelo) {
		modelo.addAttribute("categoria", new CategoryVO());
		modelo.addAttribute("user", su.findById(iduser));
		return "registrocategoria";
	}

	/**
	 * Con este controlador, añadimos una nueva categoria a nuestra BBDD
	 * 
	 * Le añadimos el usuario, que pasamos como iduser y lo buscamos y un arraylist
	 * de tareas vacio que se ira rellenando cada vez que creemos una tarea asociada
	 * a esta categoria
	 */
	@PostMapping("/submitcategoria")
	public String submitCategoria(@ModelAttribute CategoryVO category, int iduser, Model modelo) {

		category.setUser(su.findById(iduser));
		category.setTareas(new ArrayList<TaskVO>());
		sc.insertar(category);

		return "redirect:listacategorias?iduser=" + iduser;

	}

	/**
	 * Con este controlador, devolvemos la vista para modificar una categoria
	 * pasándole el id del objeto CategoryVO a modificar
	 */
	@GetMapping("/modificacategoria")
	public String modificaCategory(@RequestParam int idcategory, Model modelo) {
		modelo.addAttribute("category", sc.findById(idcategory));
		modelo.addAttribute("user", sc.findById(idcategory).getUser());
		return "modificacategoria";
	}

	/**
	 * Con este controlador, modificamos una categoria en nuestra BBDD
	 * 
	 * La pasamos como parámetro y la buscamos en la BBDD, le actualizamos los
	 * valores que hemos cambiado, y la modificamos en BBDD
	 */
	@PostMapping("/modifycategory")
	public String modifyCategory(@ModelAttribute CategoryVO category, Model modelo) {

		CategoryVO cat_mod = sc.findById(category.getIdcategory());

		cat_mod.setName(category.getName());

		sc.modificar(cat_mod);

		return "redirect:listacategorias?iduser=" + cat_mod.getUser().getIduser();

	}

}
package com.capgemini.util;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

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

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test task manager GTD_G7")
class TestJPA {

	static ServicioUser su;
	static ServicioCategory sc;
	static ServicioTask st;

	@BeforeAll
	public static void previo() {
		su = new ServicioUserImpl();
		sc = new ServicioCategoryImpl();
		st = new ServicioTaskImpl();

	}

	@Test
	@Order(1)
	void testInsertarUsuarios() {		
		System.out.println("[TEST 1]");

//		insertar unos usuarios
		su.insertar(new UserVO("paco@paco.mail", true, "paquito-priulero", "pwd-segura", UserStatus.ENABLED,
				new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));
		su.insertar(new UserVO("paco@paco.mail", true, "paquito-priulero-2", "pwd-segura", UserStatus.ENABLED,
				new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));
		
		assertEquals(2, su.findAll().size());
		
	}

	@Test
	@Order(2)
	void testInsertarCategorias() {
		System.out.println("[TEST 2]");

//		insertamos unas categorias de tarea
		sc.insertar(new CategoryVO("imprirmir", su.findById(1), new ArrayList<TaskVO>()));
		sc.insertar(new CategoryVO("recortar", su.findById(1), new ArrayList<TaskVO>()));
		
		assertEquals(2, su.findAll().size());

	}

	@Test
	@Order(3)
	void testModificarCategorias() {
		System.out.println("[TEST 3]");

//		mostramos las categorias del usuario con id 1
		System.out.println("-----mostramos las categorias del usuario con id 1------");
		List<CategoryVO> categorias = su.findById(1).getCategorias();
		for (CategoryVO c : categorias) {
			System.out.println(c.getName());
		}
		System.out.println("-----------");

//		modificamos la categoria 2
		System.out.println("-----modificamos la categoria 2------");
		CategoryVO category = sc.findById(2);
		category.setName("recortar_new");
		sc.modificar(category);
		System.out.println("nuevo nombre de la categoria 2: " + sc.findById(2).getName());

//		mostramos las categorias del usuario con id 1
		System.out.println("-----mostramos las categorias del usuario con id 1------");
		List<CategoryVO> categorias2 = su.findById(1).getCategorias();
		for (CategoryVO c : categorias2) {
			System.out.println(c.getName());
		}
		System.out.println("-----------");
		
		assertEquals("recortar_new", sc.findById(2).getName());

	}

	@Test
	@Order(4)
	void testEliminarCategoria() {
		System.out.println("[TEST 4]");

//		eliminamos la categoria 2
		System.out.println("-----eliminamos la categoria 2------");
		sc.eliminar(sc.findById(2));

//		mostramos las categorias del usuario con id 1
		System.out.println("------mostramos las categorias del usuario con id 1-----");
		List<CategoryVO> categorias3 = su.findById(1).getCategorias();
		for (CategoryVO c : categorias3) {
			System.out.println(c.getName());
		}
		System.out.println("-----------");
		
		assertNull(sc.findById(2));

	}

	@Test
	@Order(5)
	void testMostarTodosLosUsuarios() {
		System.out.println("[TEST 5]");
		
//		mostramos los usuarios
		System.out.println("-----------");
		System.out.println("Lo usuarios que hay son: ");
		List<UserVO> usuarios = su.findAll();
		for (UserVO u : usuarios) {
			System.out.println(u.getLogin());
		}
		System.out.println("-----------");

		assertEquals(2, su.findAll().size());

	}
	
	@Test
	@Order(6)
	void testInsertarTareas() {
		System.out.println("[TEST 6]");		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos", "lleva mucho tiempo", LocalDate.of(2021, 01, 01), LocalDate.of(2021, 01, 01), LocalDate.of(2021, 01, 01), su.findById(1), sc.findById(1))));
		//imprimimos las task del usuario 1
		System.out.println("Task del user 1");
		List<TaskVO> tasks_user = su.findById(1).getTareas();
		for (TaskVO t : tasks_user) {
			System.out.println(t.getTitle());
		}
		//imprimimos las task asociadadas a la categoria 1
		System.out.println("Task de la categoria "+sc.findById(1).getName());
		List<TaskVO> tasks_category = sc.findById(1).getTareas();
		for (TaskVO t : tasks_category) {
			System.out.println(t.getTitle());
		}
	}
	
	@Test
	@Order(7)
	void testBuscarUserPorLogin() {
		System.out.println("[TEST 7]");
		assertEquals("paquito-priulero", su.findByLogin("paquito-priulero").getLogin());
		System.out.println(su.findByLogin("paquito-priulero").getEmail());
	}	
	

}
package com.capgemini.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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
	@DisplayName("Insertar usuarios")
	void testInsertarUsuarios() {
		System.out.println("[TEST 1]");

//		insertar unos usuarios
		su.insertar(new UserVO("root@root.com", true, "root", "root", UserStatus.ENABLED, new ArrayList<CategoryVO>(),
				new ArrayList<TaskVO>()));
		su.insertar(new UserVO("user1@user1.com", false, "user1", "user1", UserStatus.DISABLED,
				new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));

		assertEquals(2, su.findAll().size());

	}

	@Test
	@Order(2)
	@DisplayName("Insertar categorias")
	void testInsertarCategorias() {
		System.out.println("[TEST 2]");

//		insertamos unas categorias de tarea
		sc.insertar(new CategoryVO("INBOX", su.findById(2), new ArrayList<TaskVO>()));
		sc.insertar(new CategoryVO("CATEGORIA 1", su.findById(2), new ArrayList<TaskVO>()));
		sc.insertar(new CategoryVO("CATEGORIA 2", su.findById(2), new ArrayList<TaskVO>()));

		assertEquals(3, sc.findAll().size());

	}

	@Test
	@Order(3)
	@DisplayName("Modificar categorias")
	void testModificarCategorias() {
		System.out.println("[TEST 3]");

//		mostramos las categorias del usuario con id 1
		System.out.println("-----mostramos las categorias del usuario con id 1------");
		List<CategoryVO> categorias = su.findById(2).getCategorias();
		for (CategoryVO c : categorias) {
			System.out.println(c.getName());
		}
		System.out.println("-----------");

//		modificamos la categoria 2
		System.out.println("-----modificamos la categoria 2------");
		CategoryVO category = sc.findById(2);
		category.setName("CATEGORIA 1 - MODIFY");
		sc.modificar(category);
		System.out.println("nuevo nombre de la categoria 2: " + sc.findById(2).getName());

//		mostramos las categorias del usuario con id 1
		System.out.println("-----mostramos las categorias del usuario con id 1------");
		List<CategoryVO> categorias2 = su.findById(2).getCategorias();
		for (CategoryVO c : categorias2) {
			System.out.println(c.getName());
		}
		System.out.println("-----------");

		assertEquals("CATEGORIA 1 - MODIFY", sc.findById(2).getName());

	}

	@Test
	@Order(4)
	@DisplayName("Eliminar categorias")
	void testEliminarCategoria() {
		System.out.println("[TEST 4]");

//		eliminamos la categoria 2
		System.out.println("-----eliminamos la categoria 2------");
		sc.eliminar(sc.findById(2));

//		mostramos las categorias del usuario con id 1
		System.out.println("------mostramos las categorias del usuario con id 1-----");
		List<CategoryVO> categorias3 = su.findById(2).getCategorias();
		for (CategoryVO c : categorias3) {
			System.out.println(c.getName());
		}
		System.out.println("-----------");

		assertNull(sc.findById(2));

	}

	@Test
	@Order(5)
	@DisplayName("Buscamos todos los usuarios")
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
	@DisplayName("Insertar tareas")
	void testInsertarTareas() {
		System.out.println("[TEST 6]");
		
		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos1", "lleva mucho tiempo1", LocalDate.now(), null,
				null, su.findById(2), sc.findById(1))));
		
		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos2", "lleva mucho tiempo2",
				LocalDate.of(2020, 01, 01), null, null, su.findById(2), sc.findById(1))));
		
		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos3", "lleva mucho tiempo3",
				LocalDate.of(2020, 01, 01), LocalDate.now(), LocalDate.now(), su.findById(2), sc.findById(1))));
		
		
		assertEquals(1,
				st.insertar(new TaskVO("imprimir los documentos4", "lleva mucho tiempo4", LocalDate.of(2020, 01, 01),
						LocalDate.of(2020, 01, 01), LocalDate.now(), su.findById(2), sc.findById(1))));
		
		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos5", "lleva mucho tiempo5",
				LocalDate.of(2020, 01, 01), LocalDate.now(), null, su.findById(2), sc.findById(1))));
		
		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos6", "lleva mucho tiempo6",
				LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 01), null, su.findById(2), sc.findById(1))));
		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos7", "lleva mucho tiempo7",
				LocalDate.of(2020, 01, 01), LocalDate.of(2021, 8, 12), null, su.findById(2), sc.findById(1))));
		
		
		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos8", "lleva mucho tiempo8",
				LocalDate.of(2020, 01, 01), LocalDate.of(2021, 8, 12), null, su.findById(2), sc.findById(1))));
		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos9", "lleva mucho tiempo9",
				LocalDate.of(2020, 01, 01), LocalDate.of(2021, 8, 12), null, su.findById(2), sc.findById(1))));
		
		
		assertEquals(1, st.insertar(new TaskVO("imprimir los documentos", "lleva mucho tiempo",
				LocalDate.of(2020, 01, 01), null, null, su.findById(2), sc.findById(3))));

		// imprimimos las task del usuario 1
		System.out.println("Task del user 1");
		List<TaskVO> tasks_user = su.findById(2).getTareas();
		for (TaskVO t : tasks_user) {
			System.out.println(t.getTitle());
		}
		// imprimimos las task asociadadas a la categoria 1
		System.out.println("Task de la categoria " + sc.findById(1).getName());
		List<TaskVO> tasks_category = sc.findById(1).getTareas();
		for (TaskVO t : tasks_category) {
			System.out.println(t.getTitle());
		}
	}

	@Test
	@Order(7)
	@DisplayName("Buscar usuarios por su login")
	void testBuscarUserPorLogin() {
		System.out.println("[TEST 7]");
		assertEquals("root", su.findByLogin("root").getLogin());
		System.out.println(su.findByLogin("root").getEmail());
	}

	@Test
	@Order(8)
	@DisplayName("Buscar tarea por su id")
	void testBuscarTaskPorId() {
		System.out.println("[TEST 8]");
		assertEquals("lleva mucho tiempo1", st.findById(1).getComments());
		System.out.println(st.findById(1).getComments());
	}

	@Test
	@Order(9)
	@DisplayName("Buscar tareas inbox")
	void testBuscarTaskInbox() {
		System.out.println("[TEST 9]");
		List<TaskVO> tareas = st.findAllTareasInboxByIduser(su.findById(2).getIduser(), sc.findById(1).getIdcategory());

		for (TaskVO t : tareas) {
			System.out.println(t.getComments());
		}

		assertEquals(7,
				st.findAllTareasInboxByIduser(su.findById(2).getIduser(), sc.findById(1).getIdcategory()).size());

	}

	@Test
	@Order(10)
	@DisplayName("Buscar las planeadas para hoy y las atrasadas")
	void testBuscarTaskToday() {
		System.out.println("[TEST 10]");
		List<TaskVO> tareas = st.findAllTaskDateByIdUser(su.findById(2).getIduser(), LocalDate.now());

		for (TaskVO t : tareas) {
			System.out.println(t.getComments());
		}
		assertEquals(2, st.findAllTaskDateByIdUser(su.findById(2).getIduser(), LocalDate.now()).size());
	}

	@Test
	@Order(11)
	@DisplayName("Buscar las listas por semana y las atrasadas")
	void testBuscarTaskWee() {
		System.out.println("[TEST 11]");;
		List<TaskVO> tareas = st.findAllTaskDateByIdUser(su.findById(2).getIduser(), LocalDate.now().plusDays(7));
		for (TaskVO t : tareas) {
			System.out.println(t.getComments());
		}
	}

}

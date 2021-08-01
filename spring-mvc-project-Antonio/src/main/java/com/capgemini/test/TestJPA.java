package com.capgemini.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.capgemini.modelo.CategoryVO;
import com.capgemini.modelo.TaskVO;
import com.capgemini.modelo.UserVO;
import com.capgemini.modelo.UserVO.UserStatus;
import com.capgemini.servicio.ServicioUser;
import com.capgemini.servicioImpl.ServicioUserImpl;

class TestJPA {

	static ServicioUser su;

//	@BeforeClass
//	public static void previo() {
//		su=new ServicioUserImpl();
//	}
	
//	test para hacer primeras pruebas y ver que nos crea la BBDD y las tablas

	@Test
	void test() {
		su = new ServicioUserImpl();
		su.insertar(new UserVO("paco@paco.mail",true,"paquito-priulero","pwd-segura",UserStatus.ENABLED,new ArrayList<CategoryVO>(), new ArrayList<TaskVO>()));
		assertTrue(true);
	}

}

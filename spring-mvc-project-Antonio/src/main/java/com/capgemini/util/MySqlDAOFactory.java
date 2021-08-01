package com.capgemini.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.capgemini.dao.UserDAO;
import com.capgemini.daoImpl.UserDAOImpl;

public class MySqlDAOFactory {

	EntityManagerFactory emf;
	EntityManager em;

	private static MySqlDAOFactory f;

	private MySqlDAOFactory() {

		try {
			Persistence.generateSchema("jpa", null);
			emf = Persistence.createEntityManagerFactory("jpa");
			em = emf.createEntityManager();
		} catch (Exception e) {
			System.out.println("Error al crear las tablas en la BBDD" + e.getMessage());
		}

	}

	public static MySqlDAOFactory getCon() {
		if (f == null) {
			f = new MySqlDAOFactory();
		}
		return f;
	}
	
	public UserDAO getUser() {
		return new UserDAOImpl(em);
	}

}

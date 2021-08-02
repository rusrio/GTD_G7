package com.capgemini.bussines.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.capgemini.persistence.dao.CategoryDAO;
import com.capgemini.persistence.dao.TaskDAO;
import com.capgemini.persistence.dao.UserDAO;
import com.capgemini.persistence.daoImpl.CategoryDAOImpl;
import com.capgemini.persistence.daoImpl.TaskDAOImpl;
import com.capgemini.persistence.daoImpl.UserDAOImpl;

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
	
	public CategoryDAO getCategory() {
		return new CategoryDAOImpl(em);
	}
	
	public TaskDAO getTask() {
		return new TaskDAOImpl(em);
	}

}

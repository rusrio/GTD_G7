package com.capgemini.daoImpl;

import javax.persistence.EntityManager;

import com.capgemini.dao.UserDAO;
import com.capgemini.modelo.UserVO;

public class UserDAOImpl implements UserDAO {
	
	EntityManager em;
	
	public UserDAOImpl (EntityManager em) {
		this.em=em;
	}

	
	public int insertar(UserVO usuario) {
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			System.out.println("Error al insertar usuario"+e.getMessage());
			em.getTransaction().rollback();
			return 0;
		}
	}

}

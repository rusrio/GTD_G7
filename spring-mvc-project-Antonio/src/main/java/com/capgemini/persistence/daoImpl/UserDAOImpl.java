package com.capgemini.persistence.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.capgemini.modelo.UserVO;
import com.capgemini.persistence.dao.UserDAO;

public class UserDAOImpl implements UserDAO {
	
	private EntityManager em;
	
	public UserDAOImpl (EntityManager em) {
		this.em=em;
	}

	
	public int insertar(UserVO user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			System.out.println("Error al insertar usuario"+e.getMessage());
			em.getTransaction().rollback();
			return 0;
		}
	}


	
	public int modificar(UserVO user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			System.out.println("Error al modificar el usuario con id "+user.getIduser()+" = "+e.getMessage());;
			em.getTransaction().rollback();
			return 0;
		}
	}


	
	public int eliminar(UserVO user) {
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			System.out.println("Error al eliminar el usuario con id "+user.getIduser()+" = "+e.getMessage());
			em.getTransaction().rollback();
			return 0;
		}
	}


	
	public UserVO findById(int iduser) {
		try {
			//trabajamos con alias y con el nombre de la claseVO en lugar del nombre de la tabla
			Query consulta=em.createQuery("select u from UserVO u where iduser=:iduser");
			consulta.setParameter("iduser", iduser);
			UserVO user= (UserVO) consulta.getSingleResult();
			return user;
		} catch (Exception e) {
			System.out.println("Error al buscar el usuario por su id "+e.getMessage());
			return null;
		}
	}


	
	public List<UserVO> findAll() {
		try {
			Query consulta = em.createQuery("select u from UserVO u");
			List<UserVO> users = consulta.getResultList();
			return users;
		} catch (Exception e) {
			System.out.println("Error al buscar todos los usuarios "+e.getMessage());
			return null;
		}
	}


	
	public UserVO findByLogin(String login) {
		try {
			//trabajamos con alias y con el nombre de la claseVO en lugar del nombre de la tabla
			Query consulta=em.createQuery("select u from UserVO u where login=:login");
			consulta.setParameter("login", login);
			UserVO user= (UserVO) consulta.getSingleResult();
			return user;
		} catch (Exception e) {
			System.out.println("Error al buscar el usuario por su id "+e.getMessage());
			return null;
		}
	}

}

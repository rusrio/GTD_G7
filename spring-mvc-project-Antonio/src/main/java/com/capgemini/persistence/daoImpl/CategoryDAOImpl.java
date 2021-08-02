package com.capgemini.persistence.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.capgemini.modelo.CategoryVO;
import com.capgemini.modelo.UserVO;
import com.capgemini.persistence.dao.CategoryDAO;

public class CategoryDAOImpl implements CategoryDAO {

	EntityManager em;

	public CategoryDAOImpl(EntityManager em) {
		this.em = em;
	}

	public int insertar(CategoryVO category) {
		try {
			em.getTransaction().begin();
			em.persist(category);
			em.getTransaction().commit();

			// al insertar una categoria, lo añadimos a la lista del usuario que la haya
			// creado
			UserVO user = category.getUser();
			user.getCategorias().add(category);

			return 1;
		} catch (Exception e) {
			System.out.println("Error al insertar categoria" + e.getMessage());
			em.getTransaction().rollback();
			return 0;
		}

	}

	public int modificar(CategoryVO category) {
		try {
			em.getTransaction().begin();
			//antes de modificar la categoria, buscamos la posicion en la lista del empleado que la creo
			UserVO user = category.getUser();
			List<CategoryVO> categorias = user.getCategorias();
			int posicion = categorias.indexOf(category);
			//la modificamos		
			em.merge(category);
			//y ahora la actualizamos en la lista del empleado
			categorias.set(posicion, category);			
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			System.out.println(
					"Error al modificar el categoria con id " + category.getIdcategory() + " = " + e.getMessage());
			em.getTransaction().rollback();
			return 0;
		}
	}

	public int eliminar(CategoryVO category) {
		try {
			em.getTransaction().begin();

			// antes de eliminar la categoria, la elminamos de la lista del usuario

			UserVO user = category.getUser();
			List<CategoryVO> categorias = user.getCategorias();
			categorias.remove(category);
			
			em.remove(category);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			System.out.println(
					"Error al eliminar el categoria con id " + category.getIdcategory() + " = " + e.getMessage());
			em.getTransaction().rollback();
			return 0;
		}
	}

	public CategoryVO findById(int idcategory) {
		try {
			// trabajamos con alias y con el nombre de la claseVO en lugar del nombre de la
			// tabla
			Query consulta = em.createQuery("select c from CategoryVO c where idcategory=:idcategory");
			consulta.setParameter("idcategory", idcategory);
			CategoryVO category = (CategoryVO) consulta.getSingleResult();
			return category;
		} catch (Exception e) {
			System.out.println("Error al buscar la categoria por su id " + e.getMessage());
			return null;
		}
	}

	public List<CategoryVO> findAll() {
		try {
			Query consulta = em.createQuery("select c from CategoryVO c");
			List<CategoryVO> categories = consulta.getResultList();
			return categories;
		} catch (Exception e) {
			System.out.println("Error al buscar todas las categorias " + e.getMessage());
			return null;
		}
	}

}

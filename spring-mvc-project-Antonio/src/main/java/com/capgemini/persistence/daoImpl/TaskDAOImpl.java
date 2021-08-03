package com.capgemini.persistence.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.capgemini.modelo.CategoryVO;
import com.capgemini.modelo.TaskVO;
import com.capgemini.modelo.UserVO;
import com.capgemini.persistence.dao.TaskDAO;

public class TaskDAOImpl implements TaskDAO {

	private EntityManager em;

	public TaskDAOImpl(EntityManager em) {
		this.em = em;
	}

	public int insertar(TaskVO task) {

		try {
			em.getTransaction().begin();
			em.persist(task);
			em.getTransaction().commit();

			// añadimos la tarea, a la lista de la categoria y del usuario a quien pertenece
			UserVO user = task.getUser();
			CategoryVO category = task.getCategory();

			user.getTareas().add(task);
			category.getTareas().add(task);

			return 1;
		} catch (Exception e) {
			System.out.println("Error al insertar tarea" + e.getMessage());
			em.getTransaction().rollback();
			return 0;
		}

	}

	
	public int modificar(TaskVO task) {
		try {
			em.getTransaction().begin();
			//antes de modificar la tarea, buscamos la posicion en la listas de usuario y categoria
			UserVO user= task.getUser();
			List<TaskVO> tasks_user=user.getTareas();
			int posicion_user = tasks_user.indexOf(task);
			CategoryVO category = task.getCategory();
			List<TaskVO> tasks_category = category.getTareas();
			int posicion_category=tasks_category.indexOf(task);
			//y ahora actuliazamos en ambas
			tasks_user.set(posicion_user, task);
			tasks_category.set(posicion_category, task);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			System.out.println("Error al modificar la tarea con id "+ task.getIdtask()+e.getMessage());
			em.getTransaction().rollback();
			return 0;
		}
		
	}

	
	public int eliminar(TaskVO task) {
		try {
			em.getTransaction().begin();
			
			//antes de elminar la tarea, la eliminamos de las listas de usuarios y categorias
			UserVO user= task.getUser();
			List<TaskVO> tasks_user=user.getTareas();		
			CategoryVO category = task.getCategory();
			List<TaskVO> tasks_category = category.getTareas(); 
			tasks_user.remove(task);
			tasks_category.remove(task);
			
			em.remove(task);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			System.out.println("Error al eliminar la tarea con id "+task.getIdtask()+e.getMessage());
			em.getTransaction().rollback();
			return 0;
		}
	}

	
	public TaskVO findById(int idtask) {
		try {
			Query consulta = em.createQuery("selec t from TaskVO t where idtask=:idtask");
			consulta.setParameter("idtask", idtask);
			TaskVO task= (TaskVO) consulta.getSingleResult();
			return task;
		} catch (Exception e) {
			System.out.println("Error al buscar la tarea por id "+e.getMessage());
			return null;
		}
	}

	
	public List<TaskVO> findAll() {
		try {
			Query consulta = em.createQuery("select t from TaskVO t");
			List<TaskVO> tasks = consulta.getResultList();
			return tasks;
		} catch (Exception e) {
			System.out.println("Error al buscar todas las tareas " + e.getMessage());
			return null;
		}
	}

}

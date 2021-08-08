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

	/**Método que inserta una tarea.*/
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

	/**Método que modifica una tarea pasada por parámetro.*/
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
			
			em.merge(task);
			
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

	/**Método que elimina una tarea pasada por parámetro.*/
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

	/**Método que consulta una tarea cuya id se pasa por parámetro.*/
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

	/**Método que consulta todas las tareas.*/
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
	
	/**Método que consulta todas las tareas de un usuario cuya id se pasa por parámetro.*/
	public List<TaskVO> findAllTaskInboxByIdUser(int iduser) {
		try {
			Query consulta = em.createQuery("select t from TaskVO t where iduser=:iduser");
			List<TaskVO> tasks = consulta.getResultList();
			return tasks;
		} catch (Exception e) {
			System.out.println("Error al buscar todas las tareas " + e.getMessage());
			return null;
		}
	}
	
	/**Método que consulta todas las tareas de un usuario cuya id se pasa por parámetro.*/
	public List<TaskVO> findAllTaskTodayByIdUser(int iduser) {
		try {
			Query consulta = em.createQuery("select t from TaskVO t where iduser=:iduser and planned >= cast(dateadd(d, -1, getdate()) as fechaTaskToday)"); //Consulta para seleccionar aquellas task cuya fecha de entrega, especificada en "planned" es igual o inferior a 1 días.
			List<TaskVO> tasks = consulta.getResultList();
			return tasks;
		} catch (Exception e) {
			System.out.println("Error al buscar todas las tareas " + e.getMessage());
			return null;
		}
	}
	
	/**Método que consulta todas las tareas de un usuario cuya id se pasa por parámetro.*/
	public List<TaskVO> findAllTaskWeeklyByIdUser(int iduser) {
		try {
			Query consulta = em.createQuery("select t from TaskVO t where iduser=:iduser and planned >= cast(dateadd(d, -7, getdate()) as fechaTaskWeekly)"); //Consulta para seleccionar aquellas task cuya fecha de entrega, especificada en "planned" es igual o inferior a 7 días.
			List<TaskVO> tasks = consulta.getResultList();
			return tasks;
		} catch (Exception e) {
			System.out.println("Error al buscar todas las tareas " + e.getMessage());
			return null;
		}
	}


}

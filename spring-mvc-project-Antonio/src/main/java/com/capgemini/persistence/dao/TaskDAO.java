package com.capgemini.persistence.dao;

import java.util.List;

import com.capgemini.modelo.TaskVO;

public interface TaskDAO {
	
	/**Método de capa de persistencia cuya funcionalidad es insertar una tarea en BBDD. Se pasa por parámetro un objeto TaskVO.*/
	int insertar (TaskVO task);
	
	/**Método de capa de persistencia cuya funcionalidad es modificar una tarea en BBDD. Se pasa por parámetro un objeto TaskVO.*/
	int modificar (TaskVO task);
	
	/**Método de capa de persistencia cuya funcionalidad es eliminar una tarea en BBDD. Se pasa por parámetro un objeto TaskVO.*/
	int eliminar (TaskVO task);
	
	/**Método de capa de persistencia cuya funcionalidad es buscar una tarea en BBDD. Se pasa por parámetro la id de la tarea en cuestión.*/
	TaskVO findById (int idtask);
	
	/**Método de capa de persistencia cuya funcionalidad es insertar una tarea en BBDD. Se pasa por parámetro un objeto TaskVO.*/
	List<TaskVO> findAll();

}

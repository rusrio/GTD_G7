package com.capgemini.persistence.dao;

import java.util.List;

import com.capgemini.modelo.TaskVO;

public interface TaskDAO {
	
	/**M�todo de capa de persistencia cuya funcionalidad es insertar una tarea en BBDD. Se pasa por par�metro un objeto TaskVO.*/
	int insertar (TaskVO task);
	
	/**M�todo de capa de persistencia cuya funcionalidad es modificar una tarea en BBDD. Se pasa por par�metro un objeto TaskVO.*/
	int modificar (TaskVO task);
	
	/**M�todo de capa de persistencia cuya funcionalidad es eliminar una tarea en BBDD. Se pasa por par�metro un objeto TaskVO.*/
	int eliminar (TaskVO task);
	
	/**M�todo de capa de persistencia cuya funcionalidad es buscar una tarea en BBDD. Se pasa por par�metro la id de la tarea en cuesti�n.*/
	TaskVO findById (int idtask);
	
	/**M�todo de capa de persistencia cuya funcionalidad es insertar una tarea en BBDD. Se pasa por par�metro un objeto TaskVO.*/
	List<TaskVO> findAll();

}

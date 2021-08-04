package com.capgemini.bussines.servicio;

import java.util.List;

import com.capgemini.modelo.TaskVO;

public interface ServicioTask {

	/**Servicio cuya funcionalidad es insertar un objeto TaskVO pasado por par�metro.*/
	int insertar(TaskVO task);

	/**Servicio cuya funcionalidad es modificar un objeto TaskVO pasado por par�metro.*/
	int modificar(TaskVO task);

	/**Servicio cuya funcionalidad es eliminar un objeto TaskVO pasado por par�metro.*/
	int eliminar(TaskVO task);

	/**Servicio cuya funcionalidad es buscar un objeto TaskVO a partir de su id pasada por par�metro.*/
	TaskVO findById(int idtask);

	List<TaskVO> findAll();

}
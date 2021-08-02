package com.capgemini.bussines.servicio;

import java.util.List;

import com.capgemini.modelo.TaskVO;

public interface ServicioTask {

	int insertar(TaskVO task);

	int modificar(TaskVO task);

	int eliminar(TaskVO task);

	TaskVO findById(int idtask);

	List<TaskVO> findAll();

}
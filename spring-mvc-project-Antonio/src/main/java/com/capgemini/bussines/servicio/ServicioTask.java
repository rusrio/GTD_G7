package com.capgemini.bussines.servicio;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.modelo.CategoryVO;
import com.capgemini.modelo.TaskVO;
import com.capgemini.modelo.UserVO;

public interface ServicioTask {

	int insertar(TaskVO task);

	int modificar(TaskVO task);

	int eliminar(TaskVO task);

	TaskVO findById(int idtask);

	List<TaskVO> findAll();

	List<TaskVO> findAllTareasInboxByIduser(int iduser, int idcategory);

	List<TaskVO> findAllTaskDateByIdUser(int iduser, LocalDate fecha);

	List<TaskVO> findAllTareasWeeklyByIduser(int iduser, LocalDate fechaToday, LocalDate fechaWeek);

}
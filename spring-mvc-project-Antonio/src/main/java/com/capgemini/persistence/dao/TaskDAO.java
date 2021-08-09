package com.capgemini.persistence.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.modelo.CategoryVO;
import com.capgemini.modelo.TaskVO;
import com.capgemini.modelo.UserVO;

public interface TaskDAO {
	
	int insertar (TaskVO task);
	int modificar (TaskVO task);
	int eliminar (TaskVO task);
	TaskVO findById (int idtask);
	List<TaskVO> findAll();
	List<TaskVO> findAllTaskInboxByIdUser(int iduser, int idcategory);
	List<TaskVO> findAllTaskDateByIdUser(int iduser, LocalDate fecha);
	List<TaskVO> findAllTaskWeeklyByIdUser(int iduser, LocalDate fechaToday, LocalDate fechaWeek);
}

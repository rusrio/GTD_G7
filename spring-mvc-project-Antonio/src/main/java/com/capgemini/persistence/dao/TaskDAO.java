package com.capgemini.persistence.dao;

import java.util.List;

import com.capgemini.modelo.TaskVO;

public interface TaskDAO {
	
	int insertar (TaskVO task);
	int modificar (TaskVO task);
	int eliminar (TaskVO task);
	TaskVO findById (int idtask);
	List<TaskVO> findAll();
	List<TaskVO> findAllTaskInboxByIdUser(int iduser);
	List<TaskVO> findAllTaskTodayByIdUser(int iduser);
	List<TaskVO> findAllTaskWeeklyByIdUser(int iduser);
}

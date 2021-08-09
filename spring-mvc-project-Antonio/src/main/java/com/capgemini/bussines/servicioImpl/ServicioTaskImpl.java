package com.capgemini.bussines.servicioImpl;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.bussines.servicio.ServicioTask;
import com.capgemini.bussines.util.MySqlDAOFactory;
import com.capgemini.modelo.TaskVO;
import com.capgemini.persistence.dao.TaskDAO;

public class ServicioTaskImpl implements ServicioTask {
	
	private MySqlDAOFactory f;
	private TaskDAO td;	
	
	public ServicioTaskImpl() {
		f=MySqlDAOFactory.getCon();
		td=f.getTask();
	}

	@Override
	public int insertar(TaskVO task) {
		return td.insertar(task);
	}

	@Override
	public int modificar(TaskVO task) {
		return td.modificar(task);
	}

	@Override
	public int eliminar(TaskVO task) {
		return td.eliminar(task);
	}

	@Override
	public TaskVO findById(int idtask) {
		return td.findById(idtask);
	}

	@Override
	public List<TaskVO> findAll() {
		return td.findAll();
	}

	@Override
	public List<TaskVO> findAllTareasInboxByIduser(int iduser, int idcategory) {
		return td.findAllTaskInboxByIdUser(iduser,idcategory);
	}
	

	@Override
	public List<TaskVO> findAllTareasTodayByIduser(int iduser, LocalDate fecha) {
		return td.findAllTaskTodayByIdUser(iduser,fecha);
	}
	

	@Override
	public List<TaskVO> findAllTareasWeeklyByIduser(int iduser, LocalDate fechaToday, LocalDate fechaWeek) {
		return td.findAllTaskWeeklyByIdUser(iduser,fechaToday,fechaWeek);
	}
	
	
	

}

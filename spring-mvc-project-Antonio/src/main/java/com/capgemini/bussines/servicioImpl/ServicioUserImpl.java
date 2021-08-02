package com.capgemini.bussines.servicioImpl;

import java.util.List;

import com.capgemini.bussines.servicio.ServicioUser;
import com.capgemini.bussines.util.MySqlDAOFactory;
import com.capgemini.modelo.UserVO;
import com.capgemini.persistence.dao.UserDAO;

public class ServicioUserImpl implements ServicioUser {
	
	private MySqlDAOFactory f;
	private UserDAO ud;
	
	public ServicioUserImpl() {
		f=MySqlDAOFactory.getCon();
		ud=f.getUser();
	}
	
	public int insertar(UserVO user) {
		return ud.insertar(user);
	}

	public int modificar(UserVO user) {
		return ud.modificar(user);
	}

	public int eliminar(UserVO user) {
		return ud.eliminar(user);
	}

	public UserVO findById(int iduser) {
		return ud.findById(iduser);
	}

	public List<UserVO> findAll() {
		return ud.findAll();
	}

	public UserVO findByLogin(String login) {
		return ud.findByLogin(login);
	}
	
	
	
	
	
	

}

package com.capgemini.servicioImpl;

import com.capgemini.dao.UserDAO;
import com.capgemini.modelo.UserVO;
import com.capgemini.servicio.ServicioUser;
import com.capgemini.util.MySqlDAOFactory;

public class ServicioUserImpl implements ServicioUser {
	
	private MySqlDAOFactory f;
	private UserDAO ud;
	
	public ServicioUserImpl() {
		f=MySqlDAOFactory.getCon();
		ud=f.getUser();
	}

	@Override
	public int insertar(UserVO usuario) {
		return ud.insertar(usuario);
	}
	
	

}

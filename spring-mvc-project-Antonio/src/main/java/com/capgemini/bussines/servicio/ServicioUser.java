package com.capgemini.bussines.servicio;

import java.util.List;

import com.capgemini.modelo.UserVO;

public interface ServicioUser {

	int insertar(UserVO usuario);
	int modificar(UserVO usuario);
	int eliminar(UserVO usuario);
	UserVO findById(int iduser);
	List<UserVO> findAll();
	public UserVO findByLogin(String login);

}
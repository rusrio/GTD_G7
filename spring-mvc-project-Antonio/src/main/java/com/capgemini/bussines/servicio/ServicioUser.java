package com.capgemini.bussines.servicio;

import java.util.List;

import com.capgemini.modelo.UserVO;

public interface ServicioUser {

	/**Servicio cuya funcionalidad es insertar un usuario pasado por parámetro. Se pasa por parámetro un objeto. UserVO.*/
	int insertar(UserVO usuario);
	
	/**Servicio cuya funcionalidad es modificar un usuario pasado por parámetro. Se pasa por parámetro un objeto UserVO.*/
	int modificar(UserVO usuario);
	
	/**Servicio cuya funcionalidad es eliminar un usuario pasado por parámetro. Se pasa por parámetro un objeto UserVO.*/
	int eliminar(UserVO usuario);
	
	/**Servicio cuya funcionalidad es buscar un usuario cuya id se pasa por parámetro. Se pasa por parámetro un objeto UserVO.*/
	UserVO findById(int iduser);
	
	/**Servicio cuya funcionalidad es mostrar todos los usuarios a través de una lista. Se pasa por parámetro un objeto UserVO.*/
	List<UserVO> findAll();
	
	/**Servicio cuya funcionalidad es buscar un usuario. Se pasa por parámetro el login (String) del usuario.*/
	public UserVO findByLogin(String login);
	

}
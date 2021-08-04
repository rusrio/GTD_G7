package com.capgemini.persistence.dao;

import java.util.List;

import com.capgemini.modelo.UserVO;

public interface UserDAO {
	
	/**Método de capa de persistencia cuya funcionalidad es insertar un usuario en BBDD. Se pasa por parámetro un objeto UserVO.*/
	int insertar(UserVO user);
	
	/**Método de capa de persistencia cuya funcionalidad es modificar un usuario en BBDD. Se pasa por parámetro un objeto UserVO.*/
	int modificar(UserVO user);
	
	/**Método de capa de persistencia cuya funcionalidad es eliminar un usuario en BBDD. Se pasa por parámetro un objeto UserVO.*/
	int eliminar(UserVO user);
	
	/**Método de capa de persistencia cuya funcionalidad es buscar usuario en BBDD. Se pasa por parámetro la id del usuario en cuestión.*/
	UserVO findById(int iduser);
	
	/**Método de capa de persistencia cuya funcionalidad es buscar un usuario en BBDD. Se pasa por parámetro el String login.*/
	UserVO findByLogin(String login);
	
	/**Método de capa de persistencia cuya funcionalidad es insertar un usuario en BBDD. Se pasa por parámetro un objeto UserVO.*/
	List<UserVO> findAll();

}

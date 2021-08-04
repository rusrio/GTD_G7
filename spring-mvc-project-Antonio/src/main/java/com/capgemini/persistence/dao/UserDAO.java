package com.capgemini.persistence.dao;

import java.util.List;

import com.capgemini.modelo.UserVO;

public interface UserDAO {
	
	/**M�todo de capa de persistencia cuya funcionalidad es insertar un usuario en BBDD. Se pasa por par�metro un objeto UserVO.*/
	int insertar(UserVO user);
	
	/**M�todo de capa de persistencia cuya funcionalidad es modificar un usuario en BBDD. Se pasa por par�metro un objeto UserVO.*/
	int modificar(UserVO user);
	
	/**M�todo de capa de persistencia cuya funcionalidad es eliminar un usuario en BBDD. Se pasa por par�metro un objeto UserVO.*/
	int eliminar(UserVO user);
	
	/**M�todo de capa de persistencia cuya funcionalidad es buscar usuario en BBDD. Se pasa por par�metro la id del usuario en cuesti�n.*/
	UserVO findById(int iduser);
	
	/**M�todo de capa de persistencia cuya funcionalidad es buscar un usuario en BBDD. Se pasa por par�metro el String login.*/
	UserVO findByLogin(String login);
	
	/**M�todo de capa de persistencia cuya funcionalidad es insertar un usuario en BBDD. Se pasa por par�metro un objeto UserVO.*/
	List<UserVO> findAll();

}

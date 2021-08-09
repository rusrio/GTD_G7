package com.capgemini.persistence.dao;

import java.util.List;

import com.capgemini.modelo.UserVO;

public interface UserDAO {
	
	int insertar(UserVO user);
	int modificar(UserVO user);
	int eliminar(UserVO user);
	UserVO findById(int iduser);
	UserVO findByLogin(String login);
	List<UserVO> findAll();

}

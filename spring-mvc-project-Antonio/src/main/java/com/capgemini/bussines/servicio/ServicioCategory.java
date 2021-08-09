package com.capgemini.bussines.servicio;

import java.util.List;

import com.capgemini.modelo.CategoryVO;

public interface ServicioCategory {

	int insertar(CategoryVO category);

	int modificar(CategoryVO category);

	int eliminar(CategoryVO category);

	CategoryVO findById(int idcategory);
	
	List<CategoryVO> findAllById(int iduser);

	List<CategoryVO> findAll();

}
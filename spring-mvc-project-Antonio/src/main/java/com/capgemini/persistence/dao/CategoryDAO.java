package com.capgemini.persistence.dao;

import java.util.List;

import com.capgemini.modelo.CategoryVO;

public interface CategoryDAO {
	
	int insertar(CategoryVO category);
	int modificar(CategoryVO category);
	int eliminar(CategoryVO category);
	CategoryVO findById(int idcategory);
	List<CategoryVO> findAll();
	List<CategoryVO> findAllById(int iduser);

}

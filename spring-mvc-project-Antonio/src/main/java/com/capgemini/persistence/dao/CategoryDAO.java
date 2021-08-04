package com.capgemini.persistence.dao;

import java.util.List;

import com.capgemini.modelo.CategoryVO;

public interface CategoryDAO {
	
	/**Método de capa de persistencia cuya funcionalidad es insertar unaa categoría en BBDD. Se pasa por parámetro un objeto CategoryVO.*/
	int insertar(CategoryVO category);
	
	/**Método de capa de persistencia cuya funcionalidad es modificar unaa categoría en BBDD. Se pasa por parámetro un objeto CategoryVO.*/
	int modificar(CategoryVO category);
	
	/**Método de capa de persistencia cuya funcionalidad es eliminar unaa categoría en BBDD. Se pasa por parámetro un objeto CategoryVO.*/
	int eliminar(CategoryVO category);
	
	/**Método de capa de persistencia cuya funcionalidad es buscar una categoría a partir de su id pasada por parámetro en BBDD.*/
	CategoryVO findById(int idcategory);
	
	/**Método de capa de persistencia cuya funcionalidad es mostrar a través de una lista todas las categorías en BBDD.*/
	List<CategoryVO> findAll();

}

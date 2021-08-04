package com.capgemini.persistence.dao;

import java.util.List;

import com.capgemini.modelo.CategoryVO;

public interface CategoryDAO {
	
	/**M�todo de capa de persistencia cuya funcionalidad es insertar unaa categor�a en BBDD. Se pasa por par�metro un objeto CategoryVO.*/
	int insertar(CategoryVO category);
	
	/**M�todo de capa de persistencia cuya funcionalidad es modificar unaa categor�a en BBDD. Se pasa por par�metro un objeto CategoryVO.*/
	int modificar(CategoryVO category);
	
	/**M�todo de capa de persistencia cuya funcionalidad es eliminar unaa categor�a en BBDD. Se pasa por par�metro un objeto CategoryVO.*/
	int eliminar(CategoryVO category);
	
	/**M�todo de capa de persistencia cuya funcionalidad es buscar una categor�a a partir de su id pasada por par�metro en BBDD.*/
	CategoryVO findById(int idcategory);
	
	/**M�todo de capa de persistencia cuya funcionalidad es mostrar a trav�s de una lista todas las categor�as en BBDD.*/
	List<CategoryVO> findAll();

}

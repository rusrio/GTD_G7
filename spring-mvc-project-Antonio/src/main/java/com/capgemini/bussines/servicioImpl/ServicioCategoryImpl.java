package com.capgemini.bussines.servicioImpl;

import java.util.List;

import com.capgemini.bussines.servicio.ServicioCategory;
import com.capgemini.bussines.util.MySqlDAOFactory;
import com.capgemini.modelo.CategoryVO;
import com.capgemini.persistence.dao.CategoryDAO;

public class ServicioCategoryImpl implements ServicioCategory {
	
	private MySqlDAOFactory f;
	private CategoryDAO cd;
	
	public ServicioCategoryImpl() {
		f=MySqlDAOFactory.getCon();
		cd=f.getCategory();
	}
	
	
	public int insertar(CategoryVO category) {
		return cd.insertar(category);
	}
	
	public int modificar(CategoryVO category) {
		return cd.modificar(category);
	}
	
	public int eliminar(CategoryVO category) {
		return cd.eliminar(category);
	}
	
	public CategoryVO findById(int idcategory) {
		return cd.findById(idcategory);
	}
	
	public List<CategoryVO> findAll() {
		return cd.findAll();
	}
	
	public List<CategoryVO> findAllById(int iduser) {
		return cd.findAllById(iduser);
	}
	
	

}

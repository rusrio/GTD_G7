package com.capgemini.modelo;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="categorias")
public class CategoryVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
	private int idcategory;
	private String name;
	@ManyToOne
	@JoinColumn(name="iduser")
	private UserVO user;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<TaskVO> tareas;
	
	public CategoryVO() {
		super();
	}

	public CategoryVO(String name, UserVO user, List<TaskVO> tareas) {
		super();
		this.name = name;
		this.user = user;
		this.tareas = tareas;
	}

	public CategoryVO(int idcategory, String name, UserVO user, List<TaskVO> tareas) {
		super();
		this.idcategory = idcategory;
		this.name = name;
		this.user = user;
		this.tareas = tareas;
	}

	public int getIdcategory() {
		return idcategory;
	}

	public void setIdcategory(int idcategory) {
		this.idcategory = idcategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public List<TaskVO> getTareas() {
		return tareas;
	}

	public void setTareas(List<TaskVO> tareas) {
		this.tareas = tareas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idcategory, name, tareas, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryVO other = (CategoryVO) obj;
		return idcategory == other.idcategory && Objects.equals(name, other.name)
				&& Objects.equals(tareas, other.tareas) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "CategoryVO [idcategory=" + idcategory + ", name=" + name + ", user=" + user + ", tareas=" + tareas
				+ "]";
	}



	
	
	

}

package com.capgemini.modelo;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "usuarios")
public class UserVO {

	public enum UserStatus {
		ENABLED, DISABLED
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
	private int iduser;
	private String email;
	@Type(type="yes_no")
	private boolean isAdmin;
	@Column(unique = true)
	private String login;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CategoryVO> categorias;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<TaskVO> tareas;

	public UserVO() {
		super();
	}



	public List<TaskVO> getTareas() {
		return tareas;
	}



	public void setTareas(List<TaskVO> tareas) {
		this.tareas = tareas;
	}



	public UserVO(int iduser, String email, boolean isAdmin, String login, String password, UserStatus status,
			List<CategoryVO> categorias, List<TaskVO> tareas) {
		super();
		this.iduser = iduser;
		this.email = email;
		this.isAdmin = isAdmin;
		this.login = login;
		this.password = password;
		this.status = status;
		this.categorias = categorias;
		this.tareas = tareas;
	}



	public UserVO(String email, boolean isAdmin, String login, String password, UserStatus status,
			List<CategoryVO> categorias, List<TaskVO> tareas) {
		super();
		this.email = email;
		this.isAdmin = isAdmin;
		this.login = login;
		this.password = password;
		this.status = status;
		this.categorias = categorias;
		this.tareas = tareas;
	}



	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLogin() {
		return login;
	}

	public List<CategoryVO> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoryVO> categorias) {
		this.categorias = categorias;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}



	@Override
	public int hashCode() {
		return Objects.hash(categorias, email, iduser, isAdmin, login, password, status, tareas);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		return Objects.equals(categorias, other.categorias) && Objects.equals(email, other.email)
				&& iduser == other.iduser && isAdmin == other.isAdmin && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password) && status == other.status
				&& Objects.equals(tareas, other.tareas);
	}



	@Override
	public String toString() {
		return "UserVO [iduser=" + iduser + ", email=" + email + ", isAdmin=" + isAdmin + ", login=" + login
				+ ", password=" + password + ", status=" + status + ", categorias=" + categorias + ", tareas=" + tareas
				+ "]";
	}

	

}

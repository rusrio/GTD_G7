package com.capgemini.modelo;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tareas")
public class TaskVO {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
	private int idtask;
	@Column(nullable = false)
	private String title;
	private String comments;
	private LocalDate created;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate planned;
	private LocalDate finished;
	@ManyToOne
	@JoinColumn(name="iduser")
	private UserVO user;
	@ManyToOne
	@JoinColumn(name="idcategory")
	private CategoryVO category;
	
	public TaskVO() {
		super();
	}
	public TaskVO(String title, String comments, LocalDate created, LocalDate planned, LocalDate finished, UserVO user,
			CategoryVO category) {
		super();
		this.title = title;
		this.comments = comments;
		this.created = created;
		this.planned = planned;
		this.finished = finished;
		this.user = user;
		this.category = category;
	}
	public TaskVO(int idtask, String title, String comments, LocalDate created, LocalDate planned, LocalDate finished,
			UserVO user, CategoryVO category) {
		super();
		this.idtask = idtask;
		this.title = title;
		this.comments = comments;
		this.created = created;
		this.planned = planned;
		this.finished = finished;
		this.user = user;
		this.category = category;
	}
	public int getIdtask() {
		return idtask;
	}
	public void setIdtask(int idtask) {
		this.idtask = idtask;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public LocalDate getPlanned() {
		return planned;
	}
	public void setPlanned(LocalDate planned) {
		this.planned = planned;
	}
	public LocalDate getFinished() {
		return finished;
	}
	public void setFinished(LocalDate finished) {
		this.finished = finished;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public CategoryVO getCategory() {
		return category;
	}
	public void setCategory(CategoryVO category) {
		this.category = category;
	}
	@Override
	public int hashCode() {
		return Objects.hash(category, comments, created, finished, idtask, planned, title, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskVO other = (TaskVO) obj;
		return Objects.equals(category, other.category) && Objects.equals(comments, other.comments)
				&& Objects.equals(created, other.created) && Objects.equals(finished, other.finished)
				&& idtask == other.idtask && Objects.equals(planned, other.planned)
				&& Objects.equals(title, other.title) && Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "TaskVO [idtask=" + idtask + ", title=" + title + ", comments=" + comments + ", created=" + created
				+ ", planned=" + planned + ", finished=" + finished + ", user=" + user + ", category=" + category + "]";
	}
	
	
	

}

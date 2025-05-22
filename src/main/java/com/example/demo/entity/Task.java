package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "category_id")
	private Integer categoryId;
	private String title;
	private LocalDate closing_date;
	private String memo;
	private boolean done;
	@Transient
	private String closeDate;

	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;

	public Task() {

	}

	public Task(String title, Integer categoryId, LocalDate closing_date, String memo) {
		this.title = title;
		this.categoryId = categoryId;
		this.closing_date = closing_date;
		this.memo = memo;
	}

	public Integer getId() {
		return id;
	}

	//	public void setId(Integer id) {
	//		this.id = id;
	//	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LocalDate getClosing_date() {
		return closing_date;
	}

	public void setClosing_date(LocalDate closing_date) {
		this.closing_date = closing_date;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

}

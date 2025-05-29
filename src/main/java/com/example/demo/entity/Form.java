package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "form")
public class Form {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String contact;
	private String name;
	private String email;

	@Override
	public String toString() {
		return "Form{" +
				"id=" + id +
				", title='" + title + '\'' +
				", contact='" + contact + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	public Form() {

	}

	public Form(String title, String contact, String name, String email) {
		this.title = title;
		this.contact = contact;
		this.name = name;
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

}

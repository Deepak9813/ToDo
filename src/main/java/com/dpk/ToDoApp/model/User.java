package com.dpk.ToDoApp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_tbl")
public class User {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI[auto]
	private int id;

	@Column(nullable = false)		//optional
	private String fullName;
	
	@Column(nullable = false, unique = true)		//optional
	private String email;

	@Column(nullable = false)		//optional
	private String password;
	
	
	private String role;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ToDo> todos;

	// Note @oneToMany relationship ma column create hudaina , so nalekheko @joincolumn,
	// column yo jun class(or table) ma map vaaxa[i.e todo_tbl(ToDo)]
	// ma column create hunxa
	

	// Note: front end baata input ma required rakhye null aaudaina and unique backend code bata  garna  yo @Column(nullable = false, unique = true), @Column(nullable = false) nalekhda ni huxa



}

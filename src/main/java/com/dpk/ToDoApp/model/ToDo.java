package com.dpk.ToDoApp.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "todo_tbl")
public class ToDo {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI[auto]
	private Long id;

	private String title;

	@DateTimeFormat(iso = ISO.DATE) // YYY-MM-DD format
	private LocalDate date;

	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
}

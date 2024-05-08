package com.dpk.ToDoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpk.ToDoApp.model.ToDo;
import com.dpk.ToDoApp.model.User;

@Repository							//this annotation is optional
public interface ToDoRepository extends JpaRepository<ToDo, Long>{

	List<ToDo> findByUser(User user);

	
}

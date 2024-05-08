package com.dpk.ToDoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpk.ToDoApp.model.User;

@Repository		//this annotation is optional
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

}

package com.dpk.ToDoApp.service;

import com.dpk.ToDoApp.model.User;

public interface UserService {

	User userSignup(User user);
	// User addUser(User user);

	User getUserByEmail(String email);

	void removeSessionMessage();


}

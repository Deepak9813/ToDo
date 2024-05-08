package com.dpk.ToDoApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dpk.ToDoApp.model.User;
import com.dpk.ToDoApp.repository.UserRepository;
import com.dpk.ToDoApp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Override
	public User userSignup(User user) {

		// =============== Encrypt Password ============
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// ================ set user role ==========
		user.setRole("USER");

		// return userRepo.save(user);
		User newUser = userRepo.save(user);
		return newUser;
	}

	@Override
	public User getUserByEmail(String email) {

		return userRepo.findByEmail(email);
	}

	@Override
	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg"); // ("key", value) ma key lekhne

	}

}

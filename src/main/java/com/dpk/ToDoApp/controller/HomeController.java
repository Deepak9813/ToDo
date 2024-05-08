package com.dpk.ToDoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dpk.ToDoApp.model.User;
import com.dpk.ToDoApp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String getWelcomePage() {

		return "Index";
	}

	@GetMapping("/signup")
	public String getSignup() {

		return "Signup";
	}

	@PostMapping("/saveUser")
	public String userSignup(@ModelAttribute User user, HttpSession session) {

		// check email already exist or not
		User u = userService.getUserByEmail(user.getEmail());
		if (u != null) {

			session.setAttribute("msg", "Email already exist..!!");
			return "redirect:/signup";
		}

		// now, save the user
		User usr = userService.userSignup(user);

		if (usr != null) {

			session.setAttribute("msg", "Signup Successfully"); // ("key", value)
			// return "redirect:/signup";

		} else {

			session.setAttribute("msg", "Something wrong on server"); // ("key", value)
			// return "redirect:/signup";
		}

		return "redirect:/signup";
	}

}

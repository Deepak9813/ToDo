package com.dpk.ToDoApp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dpk.ToDoApp.model.ToDo;
import com.dpk.ToDoApp.model.User;
import com.dpk.ToDoApp.service.ToDoService;
import com.dpk.ToDoApp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/todo")
public class ToDoController {

	@Autowired
	private ToDoService todoService;
	
	@Autowired
	private UserService userService;
	
	
	//loginUser = commonUser j lekhda ni vayo
	@ModelAttribute
	public User loginUser(Principal p) {
		
		String email = p.getName();
		
		//yo email baata user find garne
		User loginUser = userService.getUserByEmail(email);
		
		return loginUser;
		
	}
	
/*
	
	@GetMapping("add")
	//@PreAuthorize("hasAuthority('USER')")
	public String getAddToDoItems() {
		
		return "AddToDoItems";
	}
	
	@PostMapping("/add")
	public String saveToDoItems(@ModelAttribute ToDo todo, HttpSession session, Principal p) {
		
		//yeta set garye form baata user set garnu pardaina
		User loginUser = loginUser(p);
		todo.setUser(loginUser);
		
		
		ToDo tdo = todoService.addToDoItem(todo);
		
		if(tdo != null) {
			
			session.setAttribute("msg", "Save success");
			//return "redirect:/user/todo/add";
		}
		else {
			
			session.setAttribute("msg", "Something went wrong on server..!!");
			//return "redirect:/user/todo/add";
		}
		
		return "redirect:/user/todo/add";
		
	}
	
*/
	
	//==== OR [user/user ko id lagera form baata set garne ]==================
	
	@GetMapping("add")
	//@PreAuthorize("hasAuthority('USER')")
	public String getAddToDoItems(Principal p, Model model) {
		
		//User user = loginUser(p);
		User loginUser = loginUser(p);
		model.addAttribute("userId", loginUser.getId());
		
		return "AddToDoItems";
	}
	
	@PostMapping("/add")
	public String saveToDoItems(@ModelAttribute ToDo todo, HttpSession session) {
		
		ToDo tdo = todoService.addToDoItem(todo);
		
		if(tdo != null) {
			
			session.setAttribute("msg", "Save success");
			//return "redirect:/user/todo/add";
		}
		else {
			
			session.setAttribute("msg", "Something went wrong on server..!!");
			//return "redirect:/user/todo/add";
		}
		
		return "redirect:/user/todo/add";
		
	}
	
	@GetMapping("/list")
	public String getAllToDoItemsOfIndividualUser(Principal p, Model model) {
		
		//first login user find garne
		//User user = loginUser(p);
		User loginUser = loginUser(p);
		
		//yo user[login user] baata user ko todo list nikalne
		List<ToDo> doDoList = todoService.getAllToItemsByUser(loginUser);
		model.addAttribute("tdList", doDoList);				//("key", value)
		
		return "ListToDoItems";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteToDoItem(@PathVariable Long id, HttpSession session) {
		
		boolean f = todoService.deleteToDoItem(id);
		
		if(f) {	//true
			
			session.setAttribute("msg", "Delete Success");
			//return "redirect:/user/todo/list";
		}
		else {			
			session.setAttribute("msg", "Something went wrong on server");
			//return "redirect:/user/todo/list";
		}
		
		return "redirect:/user/todo/list";
	}
	
	
	@GetMapping("/edit")
	public String editToDoItem(@RequestParam Long id, Model model) {
		
		ToDo todo = todoService.getToDoItemById(id);
		model.addAttribute("todoModel", todo);		//("key", value)
		//here todoModel = todoObject (j) lekhda ni hunxa because ToDo ko object aauxa
		
		return "EditToDoItem";
		
	}
	
	@PostMapping("/update")
	public String updateToDoItems(@ModelAttribute ToDo todo, HttpSession session) {
		
		 ToDo tdo = todoService.updateToDoItem(todo);
		
		if(tdo != null) {
			
			session.setAttribute("msg", "Update success");
			//return "redirect:/user/todo/list";
		}
		else {
			
			session.setAttribute("msg", "Something went wrong on server..!!");
			//return "redirect:/user/todo/list";
		}
		
		return "redirect:/user/todo/list";
		
	}
	
	@GetMapping("/updateStatus/{id}")
	public String updateToDoStatus(@PathVariable Long id) {
		
		ToDo toDo = todoService.getToDoItemById(id);
		
		ToDo td = todoService.updateStatus(toDo);
		
		return "redirect:/user/todo/list";
	}
	
	
	
}

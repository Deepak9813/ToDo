package com.dpk.ToDoApp.service;

import java.util.List;

import com.dpk.ToDoApp.model.ToDo;
import com.dpk.ToDoApp.model.User;

public interface ToDoService {
	
	ToDo addToDoItem(ToDo toDo);
	
	boolean deleteToDoItem(Long id);
	
	ToDo updateToDoItem(ToDo toDo);
	
	ToDo getToDoItemById(Long id);
	
	List<ToDo> getAllToDoItems();
	
	//===== get All todo items of login user[i.e individual user] =====================	
//	List<ToDo> getAllToDoItemsOfIndividualUser(User user);
//	List<ToDo> getAllToDoItemsOfLoginUser(User user);
	List<ToDo> getAllToItemsByUser(User user);
	
	
	//ToDo updateStatus(Long id);
	ToDo updateStatus(ToDo toDo);
	
	
	

}

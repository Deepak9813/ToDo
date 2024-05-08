package com.dpk.ToDoApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpk.ToDoApp.model.ToDo;
import com.dpk.ToDoApp.model.User;
import com.dpk.ToDoApp.repository.ToDoRepository;
import com.dpk.ToDoApp.service.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoRepository todoRepo;

	@Override
	public ToDo addToDoItem(ToDo toDo) {

		// =====Status form baata set nagarye , yeta set garne [maile form baata set
		// gareko xu tesaile yeta comment gareko] ==============
		// toDo.setStatus("Incomplete");

		// return todoRepo.save(toDo);

		ToDo newToDo = todoRepo.save(toDo);
		return newToDo;
	}

	@Override
	public boolean deleteToDoItem(Long id) {

		ToDo toDo = todoRepo.findById(id).get();
		if (toDo != null) {

			todoRepo.delete(toDo);
			// todoRepo.deleteById(id);
			return true;
		}

		return false;
	}

	@Override
	public ToDo updateToDoItem(ToDo toDo) {
		
		//return todoRepo.save(toDo);
		ToDo updatedToDo = todoRepo.save(toDo);
		return updatedToDo;
	}

	@Override
	public ToDo getToDoItemById(Long id) {

		return todoRepo.findById(id).get();
	}

	@Override
	public List<ToDo> getAllToDoItems() {

		return todoRepo.findAll();
	}

	@Override
	public List<ToDo> getAllToItemsByUser(User user) {

		return todoRepo.findByUser(user);
	}

	@Override
	public ToDo updateStatus(ToDo toDo) {
		
		toDo.setStatus("Completed");
		
		return todoRepo.save(toDo);
	}

}

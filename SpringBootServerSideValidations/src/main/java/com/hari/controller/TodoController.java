package com.hari.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hari.model.Todo;
import com.hari.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String shoeTodos(ModelMap model) {
		String name = (String) model.get("name");
		model.put("todos", todoService.retriveTodos(name));
		return "list-todos";
	}
	
	@RequestMapping(value="/add-item", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, (String)model.get("name"), "", new Date(), false));
		return "add-todo-item";
	}

	@RequestMapping(value="/add-item", method = RequestMethod.POST)
	public String addTodoItem(ModelMap model,@Validated Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo-item";
		}
		todoService.addTodo((String)model.get("name"), todo.getDesc(), new Date(), todo.isDone());
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="/delete-item", method = RequestMethod.GET)
	public String deleteTodoItem(@RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:list-todos";
	}
}

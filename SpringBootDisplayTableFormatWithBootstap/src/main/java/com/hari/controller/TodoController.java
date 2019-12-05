package com.hari.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
		return "add-todo-item";
	}
	
	@RequestMapping(value="/add-item", method = RequestMethod.POST)
	public String addTodoItem(ModelMap model, @RequestParam String description) {
		todoService.addTodo((String)model.get("name"), description, new Date(), false);
		return "redirect:list-todos";
	}
}

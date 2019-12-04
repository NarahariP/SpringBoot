package com.hari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hari.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String shoeTodos(ModelMap model) {
		String name = (String) model.getAttribute("name");
		model.put("todos", todoService.retriveTodos(name));
		return "list-todos";
	}
}

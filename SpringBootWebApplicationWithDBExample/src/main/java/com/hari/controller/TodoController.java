package com.hari.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hari.model.Todo;
import com.hari.repository.TodoRepository;

@Controller
public class TodoController {

	/*
	 * @Autowired private TodoService todoService;
	 */

	@Autowired
	private TodoRepository todoRepository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String shoeTodos(ModelMap model) {
		model.put("todos", todoRepository.findByUser(getLoginUserName()));
		/* model.put("todos", todoService.retriveTodos(getLoginUserName())); */
		return "list-todos";
	}

	@RequestMapping(value = "/add-item", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoginUserName(), "", new Date(), false));
		return "add-todo-item";
	}

	@RequestMapping(value = "/add-item", method = RequestMethod.POST)
	public String addTodoItem(@Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "add-todo-item";
		}
		todo.setUser(getLoginUserName());
		todoRepository.save(todo);
		/*
		 * todoService.addTodo(getLoginUserName(), todo.getDesc(), todo.getTargetDate(),
		 * todo.isDone());
		 */
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/delete-item", method = RequestMethod.GET)
	public String deleteTodoItem(@RequestParam int id) {
		todoRepository.deleteById(id);
		/* todoService.deleteTodo(id); */
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/update-item", method = RequestMethod.GET)
	public String showTodoItem(ModelMap model, @RequestParam int id) {
		final Optional<Todo> todo = todoRepository.findById(id);
		/* final Todo todo = todoService.retriveTodo(id); */
		model.put("todo", todo.isPresent() ? todo.get() : new Todo());
		return "add-todo-item";
	}

	@RequestMapping(value = "/update-item", method = RequestMethod.POST)
	public String updateTodoItem(@Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "add-todo-item";
		}
		todo.setUser(getLoginUserName());
		todoRepository.save(todo);
		/* todoService.updateTodoItem(todo); */
		return "redirect:list-todos";
	}

	private String getLoginUserName() {
		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
}

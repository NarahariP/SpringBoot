package com.hari.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hari.model.Todo;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static int count = 3;
	
	static {
		todos.add(new Todo(1, "Hari","Boot", new Date(), false));
		todos.add(new Todo(2, "Narahari","MicroServices", new Date(), false));
		todos.add(new Todo(3, "NP","Cloud", new Date(), false));
	}
	
	public List<Todo> retriveTodos(String user){
		
		/**
		 * In Java 8
		 */
		return todos.stream()
			.filter(todo -> todo.getUser().equals(user))
			.collect(Collectors.toList());
		
		/**
		 * Before java8
		 * 
		List<Todo> filterTodos = new ArrayList<>();
		for (Todo todo : todos) {
			if(todo.getUser().equals(user)) {
				filterTodos.add(todo);
			}
		}
		return filterTodos;
		*/
	}
	
	public void addTodo(String user, String desc, Date targetDate, boolean isDone) {
		todos.add(new Todo(++count, user,desc, targetDate, isDone));
	}
	
	public void deleteTodo(int id) {
		Iterator<Todo> iterator = todos.iterator();
		while(iterator.hasNext()) {
			Todo todo = iterator.next();
			if(todo.getId() == id) {
				iterator.remove();
			}
		}
	}
}

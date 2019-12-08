package com.hari.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hari.model.Todo;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static int count = 3;

	static {
		todos.add(new Todo(1, "Hari", "Boot", new Date(), false));
		todos.add(new Todo(2, "hari", "MicroServices", new Date(), false));
		todos.add(new Todo(3, "hari", "Cloud", new Date(), false));
	}

	public List<Todo> retriveTodos(String user) {

		/**
		 * In Java 8
		 */
		return todos.stream().filter(todo -> todo.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());

		/**
		 * Before java8
		 *
		 * List<Todo> filterTodos = new ArrayList<>(); for (Todo todo : todos) {
		 * if(todo.getUser()!=null && todo.getUser().equalsIgnoreCase(user)) {
		 * filterTodos.add(todo); } } return filterTodos;
		 **/
	}

	public void addTodo(String user, String desc, Date targetDate, boolean isDone) {
		todos.add(new Todo(++count, user, desc, targetDate, isDone));
	}

	public void deleteTodo(int id) {
		/**
		 * In Java 8
		 */
		todos.removeIf(todo -> todo.getId() == id);

		/**
		 * Before Java 8
		 * 
		 * Iterator<Todo> iterator = todos.iterator(); while(iterator.hasNext()) { Todo
		 * todo = iterator.next(); if(todo.getId() == id) { iterator.remove(); } }
		 */
	}

	public Todo retriveTodo(int id) {
		return todos.stream().filter(todo -> todo.getId() == id).findAny().get();
	}

	public void updateTodoItem(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}
}

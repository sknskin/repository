package com.reactweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reactweb.dto.Todo;

@Controller
@RequestMapping(path = { "/demo" })
public class DemoController {
	
	@GetMapping(path = { "/todo-app" })
	public String showTodoApp() {
		
		return "demo/todo-app";
	}
	
	@CrossOrigin
	@PostMapping(path = { "/add-todo" })
	@ResponseBody
//	public String addTodo(@RequestBody Todo todo) {
	public List<Todo> addTodo(Todo todo, HttpServletRequest req) {
		
		ServletContext application = req.getServletContext();
		List<Todo> todos = (List<Todo>)application.getAttribute("todos");
		Integer nextId = (Integer)application.getAttribute("nextId");
		if (todos == null) {
			todos = new ArrayList<>();
			nextId = 1;
		}
		
		todo.setId(nextId);
		nextId++;
		todos.add(todo);
		
		application.setAttribute("todos", todos);
		application.setAttribute("nextId", nextId);
		
		return todos;
	}
	
	@CrossOrigin
	@GetMapping(path = { "/load-todos" })
	@ResponseBody
	public List<Todo> loadTodos(HttpServletRequest req) {
		
		ServletContext application = req.getServletContext();
		List<Todo> todos = (List<Todo>)application.getAttribute("todos");
		Integer nextId = (Integer)application.getAttribute("nextId");
		if (todos == null) {
			todos = new ArrayList<>();
			nextId = 1;
		}
		
		application.setAttribute("todos", todos);
		application.setAttribute("nextId", nextId);
		
		return todos;
		
	}




}

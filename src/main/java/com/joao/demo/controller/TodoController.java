package com.joao.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import com.joao.demo.entity.Todo;
import com.joao.demo.service.TodoService;

import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/todos")
public class TodoController {
	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@PostMapping
	public List<Todo> create(@RequestBody @Valid Todo todo) {
		try{
			return todoService.create(todo);
		}catch(IllegalArgumentException e){
			var mesageError = e.getMessage();
			throw new ResponseStatusException(HttpStatus.CONFLICT, mesageError);
		}
	}

	@GetMapping("{id}")
	public Todo findById(@PathVariable Long id){
		return todoService.findById(id);
	}
	
	@GetMapping
	public List<Todo> list() {
		return todoService.list();
	}

	@PutMapping("{id}")
	public List<Todo> update(@PathVariable Long id, @RequestBody Todo todo){
		todo.setId(id);
		return todoService.update(todo);
	}
	
	@DeleteMapping("{id}")
	public List<Todo> delete(@PathVariable Long id) {
		return todoService.delete(id);
	}
}

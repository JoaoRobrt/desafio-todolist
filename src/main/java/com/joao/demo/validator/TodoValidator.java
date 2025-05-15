package com.joao.demo.validator;

import com.joao.demo.entity.Todo;
import com.joao.demo.repository.TodoRepository;
import org.springframework.stereotype.Component;

@Component
public class TodoValidator {

    private TodoRepository todoRepository;

    public TodoValidator(TodoRepository todoRepository){
        this.todoRepository = todoRepository;

    }

    public void validate(Todo todo){
        if(titleAlredyExists(todo.getTitle())){
            throw new IllegalArgumentException("A Todo with this title already exists!");
        }
    }

    public boolean titleAlredyExists(String title){
        return todoRepository.existsByTitle(title);
    }
}

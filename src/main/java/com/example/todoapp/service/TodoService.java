package com.example.todoapp.service;

import com.example.todoapp.model.TodoItem;
import com.example.todoapp.repository.TodoRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    //private static final Logger logger= LoggerFactory.getLogger(TodoService.class);

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoItem> getAllTodoItems(){
        return todoRepository.findAll();
    }

    public Optional<TodoItem> getTodoItemById(Long id){
        return todoRepository.findById(id);
    }

    public TodoItem createOrUpdateTodoItem(TodoItem todoItem){
        return todoRepository.save(todoItem);
    }

    public void deleteTodoItemById(Long id){
        todoRepository.deleteById(id);
    }


}

package com.example.todoapp.controller;

import com.example.todoapp.model.TodoItem;
import com.example.todoapp.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TodoController {

    private static final Logger logger= LoggerFactory.getLogger(TodoController.class);
    private final TodoService todoService;


    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/api/todos",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTodoItems(){
        try{
            List<TodoItem> todoItems=new ArrayList<>();
            Iterable<TodoItem> iterable=todoService.getAllTodoItems();
            if(iterable!=null){
                iterable.forEach(todoItems::add);
            }
            return new ResponseEntity<>(todoItems, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(value = "/api/todos/{id}",
            method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTodoItemById(@PathVariable Long id){
        try{
            return new ResponseEntity<TodoItem>(todoService.getTodoItemById(id).get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(id+ "not found",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/api/todos",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTodoItem(@RequestBody TodoItem todoItem){
//        logger.debug("creating or updating the new item");
        try{
            todoService.createOrUpdateTodoItem(todoItem);
            return new ResponseEntity<String>("Entity created successfully",HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<String>("Entity creation failed",HttpStatus.CONFLICT);

        }
    }

    @RequestMapping(value = "/api/todos/{id}",
            method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateTodoItem(@PathVariable Long id,@RequestBody TodoItem todoItem){
        try {
            if(todoService.getTodoItemById(id).isEmpty()){
                return new ResponseEntity<String>("Entity Not Found",HttpStatus.NOT_FOUND);
            }
            todoItem.setId(id);
            TodoItem newTodoItem=todoService.createOrUpdateTodoItem(todoItem);
            return new ResponseEntity<String >("Entity Updated successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Entity Updation was unsuccessful",HttpStatus.CONFLICT);
        }


    }

    @RequestMapping(value = "/api/todos/{id}",
            method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> deleteTodoItemById(@PathVariable Long id) {
        try {
            if (todoService.getTodoItemById(id).isEmpty()) {
                return new ResponseEntity<String>("Entity Not Found To Delete", HttpStatus.NOT_FOUND);

            }
            todoService.deleteTodoItemById(id);
            return new ResponseEntity<String>("Entity Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Some Error Occured while Deleting", HttpStatus.CONFLICT);
        }
    }

}

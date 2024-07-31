package com.example.todoapp.repository;

import com.example.todoapp.model.TodoItem;
import com.example.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem,Long> {
    List<TodoItem> findByUser(User user);
}

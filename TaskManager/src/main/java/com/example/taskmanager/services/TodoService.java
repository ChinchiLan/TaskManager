package com.example.taskmanager.services;

import com.example.taskmanager.dtos.TodoResponseDTO;
import com.example.taskmanager.models.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface TodoService {

    List<Todo> findAll();

    Optional<Todo> findByTitle(String title);

    Optional<Todo> findById (Long id);

    void saveTodo(String title, String description, String assigneeInput, boolean urgent, boolean done, LocalDate deadline);

    void updateTodo(Long id, Todo todo, List<Long> assigneeIds, String newAssigneeName);

    void deleteTodoById(Long id);

    public List<TodoResponseDTO> getCompletedTodoDTOs();

}

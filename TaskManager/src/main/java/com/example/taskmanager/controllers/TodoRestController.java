package com.example.taskmanager.controllers;

import com.example.taskmanager.dtos.TodoResponseDTO;
import com.example.taskmanager.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class responsible for handling RESTful API requests related to completed to-do tasks.
 */
@RestController
public class TodoRestController {

    private final TodoService todoService;

    /**
     * Constructs a new TodoRestController with the specified TodoService.
     *
     * @param todoService the TodoService to be used by the controller
     */
    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Retrieves a list of completed to-do tasks as TodoResponseDTOs and returns them in the HTTP response body.
     *
     * @return a ResponseEntity containing the list of completed TodoResponseDTOs
     */
    @GetMapping("/todos/completed")
    public ResponseEntity<List<TodoResponseDTO>> showCompletedTodos() {
        List<TodoResponseDTO> completedTodos = todoService.getCompletedTodoDTOs();
        return ResponseEntity.ok(completedTodos);
    }

}

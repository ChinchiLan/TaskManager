package com.example.taskmanager.exceptionHandler;

import com.example.taskmanager.dtos.ErrorResponseDTO;
import com.example.taskmanager.exceptions.TodoAlreadyExistsException;
import com.example.taskmanager.exceptions.TodoNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleTodoAlreadyExists(TodoAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleTodoNotFound(TodoAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
    }
}

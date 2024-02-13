package com.example.taskmanager.exceptions;

public class TodoAlreadyExistsException extends AppRuntimeException{
    public TodoAlreadyExistsException(String message) {
        super(message);
    }
}

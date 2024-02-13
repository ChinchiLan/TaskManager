package com.example.taskmanager.exceptions;

public class TodoNotFoundException extends AppRuntimeException {
    public TodoNotFoundException(String message) {
        super(message);
    }
}

package com.example.taskmanager.services;

import com.example.taskmanager.models.Assignee;

import java.util.Optional;

public interface AssigneeService {

    Optional<Assignee> findByUsername(String username);

}

package com.example.taskmanager.services;

import com.example.taskmanager.models.Assignee;
import com.example.taskmanager.repositories.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssigneeServiceImpl implements AssigneeService{

    @Autowired
    private final AssigneeRepository assigneeRepository;

    public AssigneeServiceImpl(AssigneeRepository assigneeRepository) {
        this.assigneeRepository = assigneeRepository;
    }

    @Override
    public Optional<Assignee> findByUsername(String username) {
        return assigneeRepository.findByUsername(username);
    }
}

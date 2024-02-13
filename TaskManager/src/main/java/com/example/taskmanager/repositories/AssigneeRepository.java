package com.example.taskmanager.repositories;

import com.example.taskmanager.models.Assignee;
import com.example.taskmanager.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssigneeRepository extends JpaRepository<Assignee, Long> {

    Optional<Assignee> findByUsername(String username);
}

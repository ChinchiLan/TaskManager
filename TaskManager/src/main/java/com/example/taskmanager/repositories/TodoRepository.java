package com.example.taskmanager.repositories;

import com.example.taskmanager.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByTitle(String title);

    Optional<Todo> findTodoByDone(boolean done);

}

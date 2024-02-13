package com.example.taskmanager.models;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents an assignee in the task manager. Is capable of being assigned to-dos.
 * Assignees are identified by their username, email, password and can have multiple-todos assigned to them.
 */
@Entity
public class Assignee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    @Column(nullable = false)
    private String username;
    private String email;

    private String password;
    @ManyToMany(mappedBy = "assignees")
    private List<Todo> todos = new ArrayList<>();

    /**
     * Creates an empty Assignee instance.
     */
    public Assignee() {
    }
    /**
     * Creates an Assignee instance with specified details.
     *
     * @param username the assignee's username
     * @param email the assignee's email
     * @param password the assignee's password (to be stored securely)
     * @param todos the set of to-dos assigned to the assignee
     */
    public Assignee(String username, String email, String password, List<Todo> todos) {
        this.username = username;
        this.email= email;
        this.password = password;
        this.todos = todos;
    }

    public Assignee(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}

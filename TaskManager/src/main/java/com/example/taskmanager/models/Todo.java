package com.example.taskmanager.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a to-do item in the task manager.
 * Each to-do item contains details such as title, description, urgency, completion status,
 * creation date and deadline. To-dos can be associated with multiple assignees.
 */
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean urgent = false;
    @Column(nullable = false)
    private boolean done = false;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate creationDate;
    @Column(nullable = false)
    private LocalDate deadline;
    @ManyToMany
    @JoinTable(
            name = "todo_assignee",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "assignee_id")
    )
    private List<Assignee> assignees = new ArrayList<>();

    /**
     * Constructs an empty to-do instance
     */
    public Todo() {
    }
    /**
     * Constructs an empty with specified details.
     *
     * @param title the title of the to-do
     * @param description a detailed description of the to-do
     * @param urgent indicates if the to-do is urgent
     * @param done indicates if the to-do has been completed
     * @param creationDate the date and time when the to-do was created
     * @param deadline the deadline by which the to-do should be completed
     * @param assignees a list of assignees responsible for the to-do
     */
    public Todo(String title, String description, boolean urgent, boolean done, LocalDate creationDate, LocalDate deadline, List<Assignee> assignees) {
        this.title = title;
        this.description = description;
        this.urgent = urgent;
        this.done = done;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.assignees = assignees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }
}

package com.example.taskmanager.dtos;


import java.time.LocalDate;

public class TodoResponseDTO extends ResponseDTO{
    private Long id;
    private String title;
    private String description;
    private boolean urgent;
    private boolean done;
    private LocalDate creationDate;
    private LocalDate deadline;

    public TodoResponseDTO() {
    }
    public TodoResponseDTO(Long id, String title, String description, boolean urgent, boolean done, LocalDate creationDate, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.urgent = urgent;
        this.done = done;
        this.creationDate = creationDate;
        this.deadline = deadline;
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
}

package com.todo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate taskDate;
    private LocalTime taskTime;
    private String status;

    public Task() {}

    public Task(String title, String description, LocalDate taskDate, LocalTime taskTime) {
        this.title = title;
        this.description = description;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.status = "Pending"; // Default status
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getTaskDate() { return taskDate; }
    public void setTaskDate(LocalDate taskDate) { this.taskDate = taskDate; }
    public LocalTime getTaskTime() { return taskTime; }
    public void setTaskTime(LocalTime taskTime) { this.taskTime = taskTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

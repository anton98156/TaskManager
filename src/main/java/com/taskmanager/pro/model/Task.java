package com.taskmanager.pro.model;

public class Task {
    
    // Описание сущности задачи.
    private int id;
    private String name;
    private String description;
    private boolean urgency;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUrgency(boolean urgency) {
        this.urgency = urgency;
    }

    public boolean getUrgency() {
        return urgency;
    }
    
}
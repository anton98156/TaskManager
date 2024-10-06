package com.taskmanager.pro.model;

import lombok.Data;

@Data
public class Task {
    
    // Описание сущности задачи.
    private int id;
    private String name;
    private String description;
    private boolean urgency;
    private boolean importance;

    // Кастомизация метода для корректности наименования.
    public boolean getImportance() {
        return importance;
    }

}
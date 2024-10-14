package com.taskmanager.pro.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Notification {
    
    private int id;
    private String message;
    private LocalDateTime createdDateTime;
    private boolean read;
    
    // Конструкторы.
    public Notification() {
    }
    public Notification(String message) {
        this.message = message;
    }

}
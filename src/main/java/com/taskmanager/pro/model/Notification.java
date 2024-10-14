package com.taskmanager.pro.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Notification {
    
    private int id;
    private String message;
    private LocalDateTime createdDateTime;
    
    // Конструкторы.
    public Notification() {
    }
    public Notification(String message) {
        this.message = message;
    }

    // Статусы уведомления.
    public enum Status {
        READ,
        UNREAD
    }

    private Status status = Status.UNREAD; // Значение по умолчанию.
}
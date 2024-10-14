package com.taskmanager.pro.model;

import lombok.Data;

@Data
public class Notification {
    
    private int id;
    private String message;

    public Notification(String message) {
        this.message = message;
    }
}
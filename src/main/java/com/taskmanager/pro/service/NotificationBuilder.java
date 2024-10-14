package com.taskmanager.pro.service;

import org.springframework.stereotype.Service;

import com.taskmanager.pro.model.Notification;
import com.taskmanager.pro.model.Task;

@Service
public class NotificationBuilder {

    public static Notification createNotification(String message) {
        // Логика создания уведомления.
        return new Notification(message);
    }

    public static String createMessage(Task task) {
        // Создание сообщения.
        return String.format("Истек срок исполнения по задаче: '%s'", task.getName());
    }
}
package com.taskmanager.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmanager.pro.model.Notification;
import com.taskmanager.pro.model.Task;
import com.taskmanager.pro.repository.NotificationRepository;

@Service
public class NotificationBuilder {

    private final NotificationRepository notificationRepository;

    public NotificationBuilder(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public static Notification createNotification(String message) {
        // Логика создания уведомления.
        return new Notification(message);
    }

    public static String createMessage(Task task) {
        // Создание сообщения.
        return String.format("Истек срок исполнения по задаче: '%s'.", task.getName());
    }

    public List<Notification> findAll(){
        List<Notification> notifications = notificationRepository.findAll();
        return notifications;
    }

    public Notification findById(int id) {
        return notificationRepository.findById(id);
    }

    public Notification toggleRead(Notification notification) {
        return notificationRepository.toggleRead(notification);
    }
}
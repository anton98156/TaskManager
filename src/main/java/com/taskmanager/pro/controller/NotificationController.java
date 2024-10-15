package com.taskmanager.pro.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.taskmanager.pro.model.Notification;
import com.taskmanager.pro.service.NotificationBuilder;



@Controller
public class NotificationController {

    private final NotificationBuilder notificationBuilder;

    public NotificationController(NotificationBuilder notificationBuilder) {
        this.notificationBuilder = notificationBuilder;
    }
    
    // Вывод всех уведомлений.
    @GetMapping("/notifications")
    public String findAll(Model model){
        List<Notification> notifications = notificationBuilder.findAll();
        model.addAttribute("notifications", notifications);
        return "notifications";
    }

    // Переход для обновления статуса уведомления.
    @GetMapping("/notification/toggle-read/{id}")
    public String toggleReadById(@PathVariable int id, Model model) {
        Notification notification = notificationBuilder.findById(id);
        model.addAttribute("notification", notification);
        notificationBuilder.toggleRead(notification);
        return "redirect:/notifications";
    }
    
}

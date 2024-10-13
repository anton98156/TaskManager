package com.taskmanager.pro.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.taskmanager.pro.model.Notification;

@Repository
public class NotificationRepository {

    // Взаимодействие с БД.
    private final JdbcTemplate jdbc;
    
    public NotificationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    public Notification save(Notification notification) {
        String sql = "INSERT INTO notifications (name) VALUES (?)";
        jdbc.update(sql, notification.getName());
        return notification;
    }

}

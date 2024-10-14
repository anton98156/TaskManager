package com.taskmanager.pro.repository;

import java.time.LocalDateTime;
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
        String sql = "INSERT INTO notifications (message, created_date_time, status) VALUES (?, ?, ?)";
        jdbc.update(sql, notification.getMessage(), LocalDateTime.now(), Notification.Status.UNREAD);
        return notification;
    }

}

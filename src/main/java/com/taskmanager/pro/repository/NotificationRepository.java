package com.taskmanager.pro.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        String sql = "INSERT INTO notifications (message, created_date_time) VALUES (?, ?)";
        jdbc.update(sql, notification.getMessage(), LocalDateTime.now());
        return notification;
    }

    // Вывод всех уведомлений.
    public List<Notification> findAll() {
        String sql = "SELECT * FROM notifications";
        return jdbc.query(sql, notificationRowMapper);
    }

    // Поиск уведомления по ID.
    public Notification findById(int id) {
        String sql = "SELECT * FROM notifications WHERE id = ?";
        return jdbc.queryForObject(sql, notificationRowMapper, id);
    }
    
    // Изменение статуса уведомления по ID.
    public Notification toggleRead(Notification notification) {
        String sql = "UPDATE notifications SET `read` = ? WHERE id = ?";
        jdbc.update(sql, !notification.isRead(), notification.getId());
        return notification;
    }

    private RowMapper<Notification> notificationRowMapper = (r, i) -> {
        Notification rowObject = new Notification();

        try {
        rowObject.setId(r.getInt("id"));
        rowObject.setMessage(r.getString("message"));
        rowObject.setCreatedDateTime(r.getTimestamp("created_date_time").toLocalDateTime());
        rowObject.setRead(r.getBoolean("read"));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return rowObject;
    };
}

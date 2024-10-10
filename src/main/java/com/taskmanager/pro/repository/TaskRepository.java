package com.taskmanager.pro.repository;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.LocalDateTime;
import java.util.List;

import com.taskmanager.pro.model.Task;

@Repository
public class TaskRepository {
    
    // Взаимодействие с БД.
    private final JdbcTemplate jdbc;
    
    public TaskRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Сохранение новой задачи.
    public Task save(Task task) {
        String sql = new StringBuilder()
        .append("INSERT INTO tasks ")
        .append("(name, description, urgency, importance, planned_end_date_time, status, created_date_time, modified_date_time) ")
        .append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
        .toString();
        
        jdbc.update(sql, task.getName(), 
                            task.getDescription(), 
                            task.isUrgency(), 
                            task.getImportance(),
                            task.getPlannedEndDateTime(),
                            Task.Status.ACTIVE.name(), 
                            LocalDateTime.now(), 
                            LocalDateTime.now());
        
        return task;
    }

    // Вывод всех активных задач.
    public List<Task> findAllActiveTasks() {
        String sql = "SELECT * FROM tasks WHERE status = 'ACTIVE'";
        return jdbc.query(sql, taskRowMapper);
    }

    // Вывод всех архивных задач.
    public List<Task> findAllCompletedTasks() {
        String sql = "SELECT * FROM tasks WHERE status = 'COMPLETED'";
        return jdbc.query(sql, taskRowMapper);
    }

    // Поиск задачи по ID.
    public Task findById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        return jdbc.queryForObject(sql, taskRowMapper, id);
    }

    // Перемещение задачи.
    public void moveById(int id) {
        Task.Status status = findById(id).getStatus();
        String newStatus;

        try {
            if (status.equals(Task.Status.ACTIVE)) {
                newStatus = "COMPLETED";
            } else if (status.equals(Task.Status.COMPLETED)) {
                newStatus = "ACTIVE";
            } else {
                throw new IllegalArgumentException("Некорректный статус задачи: " + status);
            }

            String sql = "UPDATE tasks SET status = '" + newStatus + "', modified_date_time = ? WHERE id = ?";
            jdbc.update(sql, LocalDateTime.now(), id);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
    }

    // Обновление задачи.
    public Task updateById(Task task, int id) {
        String sql = "UPDATE tasks SET name = ?, description = ?, urgency = ?, importance = ?, modified_date_time = ? WHERE id = ?";

        jdbc.update(sql, task.getName(),
                            task.getDescription(),
                            task.isUrgency(),
                            task.getImportance(),
                            LocalDateTime.now(), id);

        return task;
    }

    // Удаление задачи.
    public void deleteById(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbc.update(sql, id);
    }
    
    private RowMapper<Task> taskRowMapper = (r, i) ->  {
        Task rowObject = new Task();

        try {
        rowObject.setId(r.getInt("id"));
        rowObject.setName(r.getString("name"));
        rowObject.setDescription(r.getString("description"));
        rowObject.setUrgency(r.getBoolean("urgency"));
        rowObject.setImportance(r.getBoolean("importance"));
        rowObject.setStatus(Task.Status.valueOf(r.getString("status")));
        rowObject.setCreatedDateTime(r.getTimestamp("created_date_time").toLocalDateTime());
        rowObject.setModifiedDateTime(r.getTimestamp("modified_date_time").toLocalDateTime());
        // rowObject.setPlannedEndDateTime(r.getTimestamp("planned_end_date_time").toLocalDateTime());
        // rowObject.setActualEndDateTime(r.getTimestamp("actual_end_date_time").toLocalDateTime());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return rowObject;
    };
}
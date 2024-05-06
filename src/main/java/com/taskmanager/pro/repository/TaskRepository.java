package com.taskmanager.pro.repository;

import com.taskmanager.pro.entity.Task;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Repository
public class TaskRepository {
    
    // Взаимодействие с БД.
    private final JdbcTemplate jdbc;
    public TaskRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Сохранение новой задачи.
    public Task save(Task task) {
        String sql = "INSERT INTO tasks (name, description, urgency) VALUES (?, ?, ?)";
        jdbc.update(sql, task.getName(), task.getDescription(), task.getUrgency());
        return task;
    }

    // Вывод всех задач.
    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";
        RowMapper<Task> taskRowMapper = (r, i) -> {
            Task rowObject = new Task();
            rowObject.setId(r.getInt("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setDescription(r.getString("description"));
            rowObject.setUrgency(r.getBoolean("urgency"));
            return rowObject;
        };

        return jdbc.query(sql, taskRowMapper);
    }

    // Поиск задачи по ID.
    public Task findById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        RowMapper<Task> taskRowMapper = (r, i) -> {
            Task rowObject = new Task();
            rowObject.setId(r.getInt("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setDescription(r.getString("description"));
            rowObject.setUrgency(r.getBoolean("urgency"));
            return rowObject;
        };

        return jdbc.queryForObject(sql, taskRowMapper, id);
    }

    // Обновление задачи.
    public Task updateById(Task task, int id) {
        String sql = "UPDATE tasks SET name = ?, description = ?, urgency = ? WHERE id = ?";
        jdbc.update(sql, task.getName(), task.getDescription(), task.getUrgency(), id);
        return task;
    }

    // Удаление задачи.
    public void deleteById(int id) {
        String sql = "DELETE FROM tasks WHERE id=?";
        jdbc.update(sql, id);
    }
    
}
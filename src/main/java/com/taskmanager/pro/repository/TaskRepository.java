package com.taskmanager.pro.repository;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
        String sql = "INSERT INTO tasks (name, description, urgency, importance, status) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(sql, task.getName(), task.getDescription(), task.isUrgency(), task.getImportance(), Task.Status.ACTIVE.name());
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
        String newStatus = new String();

        if (findById(id).getStatus().equals(Task.Status.ACTIVE)) {
            newStatus = "COMPLETED";
        } else if (findById(id).getStatus().equals(Task.Status.COMPLETED)) {
            newStatus = "ACTIVE";
        }

        String sql = "UPDATE tasks SET status = '" + newStatus + "' WHERE id = ?";
        jdbc.update(sql, id);
    }

    // Обновление задачи.
    public Task updateById(Task task, int id) {
        String sql = "UPDATE tasks SET name = ?, description = ?, urgency = ?, importance = ? WHERE id = ?";
        jdbc.update(sql, task.getName(), task.getDescription(), task.isUrgency(), task.getImportance(), id);
        return task;
    }

    // Удаление задачи.
    public void deleteById(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbc.update(sql, id);
    }
    
    private RowMapper<Task> taskRowMapper = (r, i) ->  {
        Task rowObject = new Task();
        rowObject.setId(r.getInt("id"));
        rowObject.setName(r.getString("name"));
        rowObject.setDescription(r.getString("description"));
        rowObject.setUrgency(r.getBoolean("urgency"));
        rowObject.setImportance(r.getBoolean("importance"));
        rowObject.setStatus(Task.Status.valueOf(r.getString("status"))); 
        return rowObject;
    };
}
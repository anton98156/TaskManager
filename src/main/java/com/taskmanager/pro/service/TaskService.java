package com.taskmanager.pro.service;

import org.springframework.stereotype.Service;
import com.taskmanager.pro.entity.Task;
import com.taskmanager.pro.repository.TaskRepository;
import java.util.List;

@Service

public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Реализация методов репозитория.

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findById(int id) {
        return taskRepository.findById(id);
    }

    public void updateById(Task task, int id) {
        taskRepository.updateById(task, id);
    }

    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

}

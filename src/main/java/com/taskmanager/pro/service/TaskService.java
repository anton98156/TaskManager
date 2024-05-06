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

    public Task saveTask(Task product){
        return taskRepository.save(product);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findById(int id) {
        return taskRepository.findById(id);
    }

    public void updateById(Task product, int id) {
        taskRepository.updateById(product, id);
    }

    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

}

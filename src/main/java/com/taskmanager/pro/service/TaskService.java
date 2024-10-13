package com.taskmanager.pro.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.taskmanager.pro.model.Task;
import com.taskmanager.pro.repository.TaskRepository;

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

    public List<Task> findAllActiveTasks(){
        return taskRepository.findAllActiveTasks();
    }

    public List<Task> findAllActiveImportantTasks(){
        return taskRepository.findAllActiveImportantTasks();
    }

    public List<Task> findAllActiveUrgentTasks(){
        return taskRepository.findAllActiveUrgentTasks();
    }

    public List<Task> findAllCompletedTasks(){
        return taskRepository.findAllCompletedTasks();
    }

    public Task findById(int id) {
        return taskRepository.findById(id);
    }

    public void moveById(int id) {
        taskRepository.moveById(id);
    }

    public void updateById(Task task, int id) {
        taskRepository.updateById(task, id);
    }

    public void updateOverdueById(Task task, int id) {
        taskRepository.updateOverdueById(task, id);
    }

    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

}

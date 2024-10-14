package com.taskmanager.pro.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.taskmanager.pro.model.Task;
import com.taskmanager.pro.repository.NotificationRepository;
import com.taskmanager.pro.repository.TaskRepository;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final NotificationRepository notificationRepository;

    public TaskService(TaskRepository taskRepository, NotificationRepository notificationRepository) {
        this.taskRepository = taskRepository;
        this.notificationRepository = notificationRepository;
    }

    // Реализация методов репозитория.

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findAllActiveTasks(){
        List<Task> tasks = taskRepository.findAllActiveTasks();
        updateTasksOverdue(tasks);
        return tasks;
    }

    public List<Task> findAllActiveImportantTasks(){
        List<Task> tasks = taskRepository.findAllActiveImportantTasks();
        updateTasksOverdue(tasks);
        return tasks;
    }

    public List<Task> findAllActiveUrgentTasks(){
        List<Task> tasks = taskRepository.findAllActiveUrgentTasks();
        updateTasksOverdue(tasks);
        return tasks;
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

    public void updateById(Task task) {
        taskRepository.updateById(task);
    }
    
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

    // Выделение задач с превышением срока исполнения как "просроченных".
    private void updateTasksOverdue(List<Task> tasks) {
        for (Task task : tasks) {
            if (taskRepository.checkOverdue(task)) {
                taskRepository.updateOverdueById(task);
                notificationRepository.save(NotificationBuilder.createNotification(NotificationBuilder.createMessage(task)));
            }
        }
    }

}

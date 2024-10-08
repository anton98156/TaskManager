package com.taskmanager.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import java.util.List;

import com.taskmanager.pro.model.Task;
import com.taskmanager.pro.service.TaskService;


@Controller
public class TaskController {
    
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Вывод всех задач.
    @GetMapping("/")
    public String findAll(Model model){
        List<Task> tasks = taskService.findAllActiveTasks();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    // Переход в архив.
    @GetMapping("/archive")
    public String openArchive(Model model) {
        List<Task> tasks = taskService.findAllCompletedTasks();
        model.addAttribute("tasks", tasks);
        return "archive";
    }

    // Просмотр задачи.
    @GetMapping("/task-open/{id}")
    public String findById(@PathVariable int id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task-open";
    }
    

    // Переход на форму добавления задачи.
    @GetMapping("/task-create")
    public String createTaskForm(Task task) {
        return "task-create";
    }

    // Создание задачи.
    @PostMapping("/task-create")
    public String createTask(Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    // Перемещение задачи.
    @GetMapping("/task-move/{id}")
    public String moveTask(@PathVariable int id) {
        taskService.moveById(id);
        return "redirect:/";
    }

    // Переход на форму обновления задачи.
    @GetMapping("/task-update/{id}")
    public String updateTaskForm(@PathVariable int id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task-update";
    }
    
    // Обновление задачи.
    @PostMapping("/task-update")
    public String updateTask(Task task, int id) {
        String status = checkStatus(id);
        taskService.updateById(task, id);
        return findEndpoint(status);
    }

    // Удаление задачи.
    @GetMapping("/task-delete/{id}")
    public String deleteTask(@PathVariable int id) {
        String status = checkStatus(id);
        taskService.deleteById(id);
        return findEndpoint(status);
    }

    private String checkStatus(int id) {
        Task.Status status = taskService.findById(id).getStatus();
        return status.toString();
    }

    private String findEndpoint(String status) {
        if (status.equals("ACTIVE")) {
            return "redirect:/";
        } else {
            return "redirect:/archive";
        }
    }

}
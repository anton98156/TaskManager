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
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "index";
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

    // Завершение задачи.
    @GetMapping("/task-complete/{id}")
    public String completeTask(@PathVariable int id) {
        taskService.completeById(id);
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
        taskService.updateById(task, id);
        return "redirect:/";
    }

    // Удаление задачи.
    @GetMapping("/task-delete/{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.deleteById(id);
        return "redirect:/";
    }

}
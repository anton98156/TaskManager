package com.taskmanager.pro.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.taskmanager.pro.model.Task;
import com.taskmanager.pro.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task();
    }

    @Test
    void testSaveTask() {
        when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.saveTask(task);

        assertNotNull(savedTask);
        assertEquals(task, savedTask);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testFindAllActiveTasks() {
        List<Task> activeTasks = new ArrayList<>();
        activeTasks.add(task);
        when(taskRepository.findAllActiveTasks()).thenReturn(activeTasks);

        List<Task> tasks = taskService.findAllActiveTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
        verify(taskRepository, times(1)).findAllActiveTasks();
    }
}
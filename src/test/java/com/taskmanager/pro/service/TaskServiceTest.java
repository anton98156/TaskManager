package com.taskmanager.pro.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.taskmanager.pro.model.Task;
import com.taskmanager.pro.model.Notification;
import com.taskmanager.pro.repository.NotificationRepository;
import com.taskmanager.pro.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationBuilder notificationBuilder;

    @InjectMocks
    private TaskService taskService;
    private Task task;

    @BeforeAll
    static void initializeMocks() {
        MockitoAnnotations.openMocks(TaskServiceTest.class);
    }

    @BeforeEach
    public void initializeTask() {
        task = new Task();
        task.setId(1);
        task.setName("Test Task");
        task.setDescription("Test task for unit test");
    }

    // Вспомогательный метод для повышения читаемости кода.
    private List<Task> createTaskList(Task task) {
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        return taskList;
    }

    // Вспомогательный метод для повышения читаемости кода.
    private void verifyTaskProperties(Task expectedTask, Task actualTask) {
        assertNotNull(actualTask);
        assertEquals(expectedTask, actualTask);
        assertEquals(expectedTask.getStatus(), actualTask.getStatus());
    }

    @Test
    public void testSaveTask() {
        when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.saveTask(task);

        verifyTaskProperties(task, savedTask);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testFindAllActiveTasks() {
        when(taskRepository.findAllActiveTasks()).thenReturn(createTaskList(task));

        List<Task> tasks = taskService.findAllActiveTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        verifyTaskProperties(task, tasks.get(0));
        verify(taskRepository, times(1)).findAllActiveTasks();
    }

    @Test
    public void testFindAllActiveImportantTasks() {
        task.setImportance(true);
        when(taskRepository.findAllActiveImportantTasks()).thenReturn(createTaskList(task));

        List<Task> tasks = taskService.findAllActiveImportantTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        verifyTaskProperties(task, tasks.get(0));
        assertTrue(tasks.get(0).getImportance());
        verify(taskRepository, times(1)).findAllActiveImportantTasks();
    }

    @Test
    public void testFindAllActiveUrgentTasks() {
        task.setUrgency(true);
        when(taskRepository.findAllActiveUrgentTasks()).thenReturn(createTaskList(task));

        List<Task> tasks = taskService.findAllActiveUrgentTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        verifyTaskProperties(task, tasks.get(0));
        assertTrue(tasks.get(0).isUrgency());
        verify(taskRepository, times(1)).findAllActiveUrgentTasks();
    }

    @Test
    public void testFindAllCompletedTasks() {
        task.setStatus(Task.Status.COMPLETED);
        when(taskRepository.findAllCompletedTasks()).thenReturn(createTaskList(task));

        List<Task> tasks = taskService.findAllCompletedTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        verifyTaskProperties(task, tasks.get(0));
        verify(taskRepository, times(1)).findAllCompletedTasks();
    }

    @Test
    public void testFindById() {
        when(taskRepository.findById(1)).thenReturn(task);

        Task savedTask = taskService.findById(1);

        verifyTaskProperties(task, savedTask);
        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testMoveById() {
        taskService.moveById(1);
        verify(taskRepository, times(1)).moveById(1);
    }

    @Test
    public void testUpdateById() {
        task.setName("Updated Test");
        task.setDescription("Updated description");

        taskService.updateById(task);
        verify(taskRepository, times(1)).updateById(task);
        when(taskRepository.findById(1)).thenReturn(task);

        Task updatedTask = taskService.findById(1);

        assertNotNull(updatedTask);
        assertEquals("Updated Test", updatedTask.getName());
        assertEquals("Updated description", updatedTask.getDescription());
        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testDeleteById() {
        taskService.deleteById(1);
        verify(taskRepository, times(1)).deleteById(1);
    }

    @Test
    public void testUpdateTasksOverdue() {
        task.setPlannedEndDateTime(LocalDateTime.now().minusDays(1));
        task.setOverdue(false);
        List<Task> tasks = List.of(task);

        // Настройка мока для проверки "просрока" задачи, создания сообщения и объекта уведомления.
        when(taskRepository.checkOverdue(task)).thenReturn(true);
        String message = "Истек срок исполнения по задаче: 'Test Task'.";
        when(notificationBuilder.createMessage(task)).thenReturn(message);
        Notification notification = new Notification(message);
        when(notificationBuilder.createNotification(message)).thenReturn(notification);

        taskService.updateTasksOverdue(tasks);

        // Проверка, что статус задачи обновлен и уведомление сохранено.
        verify(taskRepository).updateOverdueById(task);
        verify(notificationRepository).save(notification);
    }

}
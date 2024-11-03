package com.taskmanager.pro.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taskmanager.pro.model.Task;
import com.taskmanager.pro.repository.NotificationRepository;

@ExtendWith(MockitoExtension.class)
public class NotificationBuilderTest {
    
    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationBuilder notificationBuilder;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task();
        task.setId(1);
        task.setName("Task for test");
    }

    @Test
    public void testCreateMessage() {
        when(notificationBuilder.createMessage(task)).thenReturn("Истек срок исполнения по задаче: 'Task for test'.");

        String createdString = notificationBuilder.createMessage(task);

        assertNotNull(createdString);
        assertEquals("Истек срок исполнения по задаче: 'Task for test'.", createdString);
    }
}
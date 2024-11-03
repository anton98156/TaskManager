package com.taskmanager.pro.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taskmanager.pro.model.Notification;
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
        task.setName("Test Task");
        task.setDescription("Description of test task");
    }

    @Test
    public void testCreateNotification() {
        String message = "Истек срок исполнения по задаче: 'Test Task'.";
        Notification notification = notificationBuilder.createNotification(String.format("Истек срок исполнения по задаче: '%s'.", task.getName()));

        assertNotNull(notification);
        assertEquals(message, notification.getMessage());
    }

    @Test
    public void testCreateMessage() {
        String expectedMessage = "Истек срок исполнения по задаче: 'Test Task'.";
        String actualMessage = notificationBuilder.createMessage(task);

        assertNotNull(actualMessage);
        assertEquals(expectedMessage, actualMessage);
    }
}
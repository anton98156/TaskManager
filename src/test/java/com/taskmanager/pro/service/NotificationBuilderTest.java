package com.taskmanager.pro.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

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
    private Notification notification;

    @BeforeEach
    public void setUp() {
        task = new Task();
        notification = new Notification();

        task.setId(1);
        task.setName("Test Task");
        task.setDescription("Description of test task");

        notification.setId(1);
        notification.setMessage(String.format("Истек срок исполнения по задаче: '%s'.", task.getName()));
        notification.setRead(false);
    }

        // Вспомогательный метод для повышения читаемости кода.
    private List<Notification> createNotificationsList(Notification notification) {
        List<Notification> notificationsList = new ArrayList<>();
        notificationsList.add(notification);
        return notificationsList;
    }

    @Test
    public void testCreateNotification() {
        Notification actualNotification = notificationBuilder.createNotification(notificationBuilder.createMessage(task));

        assertNotNull(actualNotification);
        assertEquals(notification.getMessage(), actualNotification.getMessage());
    }

    @Test
    public void testCreateMessage() {
        String actualMessage = notificationBuilder.createMessage(task);

        assertNotNull(actualMessage);
        assertEquals(notification.getMessage(), actualMessage);
    }

    @Test
    public void testFindAll() {
        when(notificationRepository.findAll()).thenReturn(createNotificationsList(notification));

        List<Notification> notifications = notificationBuilder.findAll();

        assertNotNull(notifications);
        assertEquals(1, notifications.size());
        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        when(notificationRepository.findById(1)).thenReturn(notification);

        Notification actualNotification = notificationBuilder.findById(1);

        assertNotNull(actualNotification);
        assertEquals(notification, actualNotification);
        verify(notificationRepository, times(1)).findById(1);
    }

    @Test
    public void testToggleRead() {
        notification.setRead(true);
        Notification notificationCopy = new Notification();

        notificationCopy.setId(1);
        notificationCopy.setMessage(notification.getMessage());
        notificationCopy.setRead(notification.isRead());

        when(notificationRepository.toggleRead(notification)).thenReturn(notificationCopy);

        Notification actualNotification = notificationBuilder.toggleRead(notification);
        assertEquals(notification.isRead(), actualNotification.isRead());
        verify(notificationRepository, times(1)).toggleRead(notification);
    }
}
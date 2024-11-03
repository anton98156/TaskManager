package com.taskmanager.pro.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taskmanager.pro.model.Notification;
import com.taskmanager.pro.repository.NotificationRepository;

@ExtendWith(MockitoExtension.class)
public class NotificationBuilderTest {
    
    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private Notification notification;

    @BeforeEach
    public void setUp() {
        notification = new Notification();
        notification.setId(1);
        notification.setMessage("Message for test");
    }
}
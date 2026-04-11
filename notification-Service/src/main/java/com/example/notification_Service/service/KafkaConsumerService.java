package com.example.notification_Service.service;

import com.example.notification_Service.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "notification", groupId = "notification-group")
    public void consume(NotificationDto notification) {

        System.out.println("Received event: " + notification);

        // delegate to your service
        notificationService.sendMail(notification);
    }
}


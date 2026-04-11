package com.project.auth_Service.service;

import com.project.auth_Service.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, NotificationDto> kafkaTemplate;

    public void sendNotification(NotificationDto notificationDto){
        kafkaTemplate.send("notification", notificationDto);
    }

}

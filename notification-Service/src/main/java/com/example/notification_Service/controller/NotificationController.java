package com.example.notification_Service.controller;

import com.example.notification_Service.dto.NotificationDto;
import com.example.notification_Service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody NotificationDto notification){
        notificationService.sendMail(notification);
    }
}

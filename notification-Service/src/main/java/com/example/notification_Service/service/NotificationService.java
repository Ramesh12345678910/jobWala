package com.example.notification_Service.service;

import com.example.notification_Service.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(NotificationDto notification){
        SimpleMailMessage email=new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(notification.getTo());
        email.setSubject(notification.getSubject());
        email.setText(notification.getBody());
        javaMailSender.send(email);
    }
}

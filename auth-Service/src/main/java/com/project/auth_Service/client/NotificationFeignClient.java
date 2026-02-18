package com.project.auth_Service.client;

import com.project.auth_Service.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-Service")
public interface NotificationFeignClient {

    @PostMapping("/email/send")
    void sendEmail(@RequestBody NotificationDto notification);
}


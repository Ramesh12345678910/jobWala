package com.project.profile_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-Service")
public interface UserClient {
    @GetMapping("/auth/users/{userId}/exists")
    Boolean isUserExists(@PathVariable Integer userId);
}

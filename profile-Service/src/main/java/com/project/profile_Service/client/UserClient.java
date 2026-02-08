package com.project.profile_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AUTH-SERVICE",configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/user/{userId}/exists")
    Boolean isUserExists(@PathVariable Integer userId);
}

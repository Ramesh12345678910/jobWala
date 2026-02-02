package com.project.application_Service.client;

import com.project.application_Service.dto.UserDto;
import jakarta.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-Service")
public interface AuthClient {

    @GetMapping("/users/{id}")
    UserDto getUserById(@PathVariable @Min(value=1,message="user Id must not  be 0 or less") Integer id);
}


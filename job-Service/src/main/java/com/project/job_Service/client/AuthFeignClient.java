package com.project.job_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-Service",configuration = FeignConfig.class)
public interface AuthFeignClient {

    @GetMapping("/user/validate-employer/{employerId}")
    Boolean isEmployer(@PathVariable Integer employerId);
}


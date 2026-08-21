package com.example.employee_service.client;

import com.example.employee_service.Config.FeignErrorDecoder;
import com.example.employee_service.Config.FeignRetryConfig;
import com.example.employee_service.Model.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service",
configuration ={// FeignRetryConfig.class,
        FeignErrorDecoder.class})
public interface DepartmentClient {
    @GetMapping("/departments/{id}")
    Department getDepartmentById(@PathVariable("id") String id);
}

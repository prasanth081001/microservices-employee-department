package com.example.employee_service.Service;

import com.example.employee_service.Model.Department;
import com.example.employee_service.client.DepartmentClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceCaller {
    private final DepartmentClient departmentClient;

    public DepartmentServiceCaller(DepartmentClient departmentClient) {
        this.departmentClient = departmentClient;
    }

    @CircuitBreaker(
            name = "departmentService",
            fallbackMethod = "departmentFallback"
    )
    public Department getDepartment(String departmentId) {

        System.out.println("Calling Department Service through Circuit Breaker...");

        return departmentClient.getDepartmentById(departmentId);
    }

    public Department departmentFallback(
            String departmentId,
            Throwable throwable) {

        System.out.println(
                "Circuit Breaker Fallback: Department Service unavailable"
        );

        System.out.println(
                "Reason: " + throwable.getMessage()
        );

        return null;
    }
}

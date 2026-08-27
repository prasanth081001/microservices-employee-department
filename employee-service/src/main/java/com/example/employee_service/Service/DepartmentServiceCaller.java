package com.example.employee_service.Service;

import com.example.employee_service.Model.Department;
import com.example.employee_service.client.DepartmentRestClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceCaller {
    private final DepartmentRestClient departmentRestClient;
    public DepartmentServiceCaller(DepartmentRestClient departmentRestClient) {
        this.departmentRestClient=departmentRestClient;
    }
    @Retry(name = "departmentService")
    @CircuitBreaker(
            name = "departmentService",
            fallbackMethod = "departmentFallback"
    )
    public Department getDepartment(String departmentId) {

        System.out.println("Calling Department Service through Circuit Breaker...");

        return departmentRestClient.getDepartmentById(departmentId);
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

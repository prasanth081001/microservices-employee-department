package com.example.employee_service.client;

import com.example.employee_service.Model.Department;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DepartmentRestClient {

    private final RestClient restClient;
    private final HttpServletRequest request;

    public DepartmentRestClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder,HttpServletRequest request) {
        this.restClient = builder
                .baseUrl("http://department-service")
                .build();
        this.request=request;
    }

    public Department getDepartmentById(String departmentId) {

        String correlationId =
                request.getHeader("X-Correlation-ID");

        return restClient.get()
                .uri("/departments/{id}", departmentId)
                .header("X-Correlation-ID", correlationId)
                .retrieve()
                .body(Department.class);
    }
}
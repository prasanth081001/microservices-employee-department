package com.example.employee_service.client;

import com.example.employee_service.Model.Department;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DepartmentRestClient {

    private final RestClient restClient;

    public DepartmentRestClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://department-service")
                .build();
    }

    public Department getDepartmentById(String departmentId) {

        return restClient.get()
                .uri("/departments/{id}", departmentId)
                .retrieve()
                .body(Department.class);
    }
}
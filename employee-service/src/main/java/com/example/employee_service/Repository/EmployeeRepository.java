package com.example.employee_service.Repository;

import com.example.employee_service.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee,String> {
}

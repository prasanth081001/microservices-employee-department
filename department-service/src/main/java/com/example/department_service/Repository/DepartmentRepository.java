package com.example.department_service.Repository;

import com.example.department_service.Model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department,String> {
}

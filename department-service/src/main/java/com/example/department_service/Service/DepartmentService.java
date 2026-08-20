package com.example.department_service.Service;

import com.example.department_service.Model.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(String id);

    Department updateDepartment(String id, Department department);

    void deleteDepartment(String id);
}

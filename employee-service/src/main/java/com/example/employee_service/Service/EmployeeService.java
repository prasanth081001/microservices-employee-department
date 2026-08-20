package com.example.employee_service.Service;

import com.example.employee_service.Model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee getEmployeeById(String id);
    Employee updateEmployee(String id,Employee employee);
    void deleteEmployee(String id);
}

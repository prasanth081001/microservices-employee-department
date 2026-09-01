package com.example.employee_service.Service;

import com.example.employee_service.DTO.EmployeeRequest;
import com.example.employee_service.Model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(EmployeeRequest employeeRequest);
    List<Employee> getAllEmployee();
    Employee getEmployeeById(String id);
    Employee updateEmployee(String id,EmployeeRequest employeeRequest);
    void deleteEmployee(String id);
}

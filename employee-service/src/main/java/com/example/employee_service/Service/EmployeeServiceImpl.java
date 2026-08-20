package com.example.employee_service.Service;

import com.example.employee_service.Exception.EmployeeNotFoundException;
import com.example.employee_service.Model.Department;
import com.example.employee_service.Model.Employee;
import com.example.employee_service.Repository.EmployeeRepository;
import com.example.employee_service.client.DepartmentClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
   private final DepartmentServiceCaller departmentServiceCaller;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,DepartmentServiceCaller departmentServiceCaller){
        this.employeeRepository=employeeRepository;
        this.departmentServiceCaller=departmentServiceCaller;
    }
    @Override
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found " + id
                        ));

        System.out.println("calling department service...");

        Department department =
                departmentServiceCaller.getDepartment(
                        employee.getDepartmentId()
                );

        if (department != null) {

            System.out.println(
                    "Department received: " + department.getName()
            );

            employee.setDepartment(department);

        } else {

            employee.setDepartment(null);
        }

        return employee;
    }
    @Override
    public Employee updateEmployee(String id,Employee employee){
        Employee existingEmployee=employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee not found"+id));
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setSalary(employee.getSalary());
        return employeeRepository.save(existingEmployee);
    }
    @Override
    public void deleteEmployee(String id){
        if (!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException("Employee not found"+id);

        }
        employeeRepository.deleteById(id);
    }



}

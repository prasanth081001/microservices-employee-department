package com.example.employee_service.Controller;

import com.example.employee_service.DTO.EmployeeRequest;
import com.example.employee_service.Model.Employee;
import com.example.employee_service.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminTest() {
        return ResponseEntity.ok("Admin access successful");
    }
   @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Valid @RequestBody EmployeeRequest employeeRequest) {

        return new ResponseEntity<>(
                employeeService.createEmployee(employeeRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(
                employeeService.getAllEmployee()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable String id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable String id,
            @Valid @RequestBody EmployeeRequest employeeRequest) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, employeeRequest)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable String id) {

        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

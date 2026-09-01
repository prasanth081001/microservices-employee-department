package com.example.department_service.Controller;

import com.example.department_service.Model.Department;
import com.example.department_service.Service.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final AtomicInteger attemptCounter=new AtomicInteger(0);
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(
            @RequestBody Department department) {

        return new ResponseEntity<>(
                departmentService.createDepartment(department),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(
                departmentService.getAllDepartments()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(
            @PathVariable String id, HttpServletRequest request) throws InterruptedException {
        System.out.println("Department API called");

        String correlationId =
                request.getHeader("X-Correlation-ID");

        System.out.println(
                "Correlation ID: " + correlationId
        );
        Thread.sleep(1000);
       int attempt = attemptCounter.incrementAndGet();

        System.out.println("Department API attempt: " + attempt);

        if (attempt <= 2) {
            System.out.println("Department Service: Temporary failure");
            throw new RuntimeException("Temporary failure - testing retry");
        }

        System.out.println("Department Service: SUCCESS");

        attemptCounter.set(0);

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable String id,
            @RequestBody Department department) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, department)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable String id) {

        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}

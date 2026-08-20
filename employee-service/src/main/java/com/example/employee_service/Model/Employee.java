package com.example.employee_service.Model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")

public class Employee {
    @Id

    private String id;
    private String name;
    private String email;
    private String departmentId;
    private String designation;
    private double salary;

    @Transient
    private Department department;

    public Employee(){
        super();
    }

    public Employee(String id, String name, String email, String departmentId, String designation, double salary,Department department) {
       super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.designation = designation;
        this.salary = salary;
        this.department=department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String department) {
        this.departmentId = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

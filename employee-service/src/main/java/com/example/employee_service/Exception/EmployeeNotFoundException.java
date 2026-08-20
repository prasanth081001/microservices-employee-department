package com.example.employee_service.Exception;

public class EmployeeNotFoundException extends  RuntimeException{
    public EmployeeNotFoundException(String message){
        super(message);
    }
}

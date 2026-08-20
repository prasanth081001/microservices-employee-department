package com.example.department_service.Service;

import com.example.department_service.Model.Department;
import com.example.department_service.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(String id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));
    }

    @Override
    public Department updateDepartment(String id, Department department) {

        Department existingDepartment =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Department not found"));

        existingDepartment.setName(department.getName());
        existingDepartment.setDescription(department.getDescription());

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(String id) {

        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found");
        }

        departmentRepository.deleteById(id);
    }
}

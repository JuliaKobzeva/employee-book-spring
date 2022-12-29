package com.skypro.employeebooknew.repositories;

import com.skypro.employeebooknew.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeRepository {
    private int id;
    private final List<Employee> employees;

    public EmployeeRepository(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
        ++id;
    }

    public int getId() {
        return id;
    }
}

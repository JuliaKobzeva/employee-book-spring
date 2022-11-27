package com.skypro.employeebooknew.service;

import com.skypro.employeebooknew.model.Employee;
import com.skypro.employeebooknew.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees(){
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest){
        if(employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null){
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .sum();
    }
    public Employee getSalaryMin() {
        return employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .get();
    }
    public Employee getSalaryMax() {
        return employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
    }

    public List<Employee> getSalaryHigh() {

        double sr = employees.values().stream()
                .mapToInt(Employee->Employee.getSalary())
                .average()
                .orElse(0);

        return employees.values().stream()
                .filter(x -> x.getSalary() > sr)
                .collect(Collectors.toList());
    }
}

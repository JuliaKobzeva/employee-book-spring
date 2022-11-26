package com.skypro.employeebooknew.service;

import com.skypro.employeebooknew.model.Employee;
import com.skypro.employeebooknew.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public int getSalaryMin() {
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .min()
                .getAsInt();
    }
    public int getSalaryMax() {
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .max()
                .getAsInt();
    }
    public double getSalaryAverage() {
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .average()
                .getAsDouble();
    }
    public List<String> getSalaryHigh() {

        double sr = employees.values().stream()
                .mapToInt(Employee->Employee.getSalary())
                .average()
                .orElse(0);

        return employees.values().stream()
                .filter(x -> x.getSalary() > sr)
                .map(x -> x.getLastName())
                .collect(Collectors.toList());
    }
}

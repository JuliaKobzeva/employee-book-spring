package com.skypro.employeebooknew.service;

import com.skypro.employeebooknew.model.Employee;
import com.skypro.employeebooknew.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
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
        Employee employee = new Employee(employeeRequest.getId(),
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getSalary(),
                employeeRequest.getDepartment());

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

    static Employee createEmployeeFromRequest(EmployeeRequest employeeRequest, int id){
        validateEmployeeData(employeeRequest);
        return new Employee(++id,
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
    }

    static void validateEmployeeData(EmployeeRequest employeeRequest){
        final String firstName = employeeRequest.getFirstName();
        final String lastName = employeeRequest.getLastName();
        checkName(firstName,lastName);
        employeeRequest.setFirstName(StringUtils.capitalize(firstName));
        employeeRequest.setLastName(StringUtils.capitalize(lastName));
    }

    static void checkName(String firstName,String lastName){
        if(StringUtils.isEmpty(firstName)||StringUtils.isEmpty(lastName)
            ||!firstName.chars().allMatch(Character::isLetter)
            ||!lastName.chars().allMatch(Character::isLetter)){
            throw new RuntimeException();
        }
    }

    static int averageSalary(List<Employee> employees){
        return employees.stream()
        .mapToInt(Employee::getSalary)
        .sum()
        /employees.size();
    }

}

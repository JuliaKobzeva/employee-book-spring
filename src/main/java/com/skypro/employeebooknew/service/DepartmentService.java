package com.skypro.employeebooknew.service;

import com.skypro.employeebooknew.model.Employee;
import com.skypro.employeebooknew.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeRepository employeeRepository;

    public DepartmentService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Set<Integer> getExistingDepartment(){
        return employeeRepository.getEmployees().stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
    }

    public List<Employee> getEmployeesFromDepartment(int departmentId){
        return employeeRepository.getEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public int getSalarySumOfDepartment(int department){
        return getEmployeesFromDepartment(department).stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Map<Integer, List<Employee>> getEmployeesByDepartment(){
        return getExistingDepartment().stream()
                .collect(Collectors.toMap(dept -> dept, this::getEmployeesFromDepartment));
    }

    public int getMaxSalaryOfDepartment(int departmentId){
        return getEmployeesFromDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary)
                .max().orElseThrow();
    }

    public int getMinSalaryOfDepartment(int departmentId){
        return getEmployeesFromDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary)
                .min().orElseThrow();
    }

}

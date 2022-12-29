package com.skypro.employeebooknew.controller;

import com.skypro.employeebooknew.model.Employee;
import com.skypro.employeebooknew.record.EmployeeRequest;
import com.skypro.employeebooknew.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return this.employeeService.addEmployee(employeeRequest);
    }
    @GetMapping("/employees/salary/sum")
    public int getSalarySum(){
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public Employee getSalaryMin(){
        return this.employeeService.getSalaryMin();
    }

    @GetMapping("/employees/salary/max")
    public Employee getSalaryMax(){
        return this.employeeService.getSalaryMax();
    }

    @GetMapping("/employees/high-salary")
    public List<Employee> getSalaryHigh(){
        return this.employeeService.getSalaryHigh();
    }
}

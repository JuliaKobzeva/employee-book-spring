package com.skypro.employeebooknew.controller;

import com.skypro.employeebooknew.model.Employee;
import com.skypro.employeebooknew.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesFromDepartment(@RequestBody int departmentId){
        return this.departmentService.getEmployeesFromDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSalarySumOfDepartment(@RequestBody int department){
        return this.departmentService.getSalarySumOfDepartment(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployeesByDepartment(){
        return this.departmentService.getEmployeesByDepartment();
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryOfDepartment(@RequestBody int departmentId){
        return this.departmentService.getMaxSalaryOfDepartment( departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public int getMinSalaryOfDepartment(@RequestBody int departmentId){
        return this.departmentService.getMinSalaryOfDepartment(departmentId);
    }
}

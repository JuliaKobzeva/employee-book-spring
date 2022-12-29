package com.skypro.employeebooknew.service;

import com.skypro.employeebooknew.model.Employee;
import com.skypro.employeebooknew.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private List<Employee> actualEmployees;

    @BeforeEach
    public void setUp(){
        Employee employee1 = new Employee(1,"Petr","Ivanov",40_000,1);
        Employee employee2 = new Employee(1,"Ivan","Petrov",40_000,1);
        Employee employee3 = new Employee(1,"Vika","Loseva",40_000,1);

        actualEmployees = new ArrayList<>(List.of(employee1, employee2, employee3));
        when(employeeRepository.getEmployees()).thenReturn(actualEmployees);
    }

    @Test
    public void ShouldReturnExistingDepartment(){
        final Set<Integer> actual = actualEmployees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
        final Set<Integer> expected = departmentService.getExistingDepartment();

        assertEquals(expected,actual);
    }

    @Test
    public void ShouldReturnEmployeesFromDepartment(){
        final int departmentId = 1;

        final List<Employee> actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
        final List<Employee> expected = departmentService.getEmployeesFromDepartment(departmentId);

        assertEquals(expected,actual);
    }

    @Test
    public void ShouldReturnSalarySumOfDepartment(){
        final int departmentId = 1;

        final int actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .sum();
        final int expected = departmentService.getSalarySumOfDepartment(departmentId);

        assertEquals(expected,actual);
    }

    @Test
    public void ShouldReturnEmployeesByDepartment(){
        final Map<Integer, List<Employee>> actual = actualEmployees.stream()
                .map(Employee::getDepartment).collect(Collectors.toSet()).stream()
                .collect(Collectors.toMap(dept -> dept,
                        dept -> actualEmployees.stream().filter(e -> e.getDepartment() == dept)
                                .collect(Collectors.toList())));
        final Map<Integer, List<Employee>> expected = departmentService.getEmployeesByDepartment();

        assertEquals(expected,actual);
    }

    @Test
    public void ShouldReturnMinSalaryOfDepartment(){
        final int departmentId = 1;

        final int actual = actualEmployees.stream().filter(e -> e.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary).min().orElse(0);
        final int expected = departmentService.getMinSalaryOfDepartment(departmentId);

        assertEquals(expected,actual);
    }

    @Test
    public void ShouldReturnMaxSalaryOfDepartment(){
        final int departmentId = 1;

        final int actual = actualEmployees.stream().filter(e -> e.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary).max().orElse(0);
        final int expected = departmentService.getMaxSalaryOfDepartment(departmentId);

        assertEquals(expected,actual);
    }

}

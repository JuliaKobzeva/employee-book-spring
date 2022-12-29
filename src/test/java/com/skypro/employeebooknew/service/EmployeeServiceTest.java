package com.skypro.employeebooknew.service;

import com.skypro.employeebooknew.model.Employee;
import com.skypro.employeebooknew.record.EmployeeRequest;
import com.skypro.employeebooknew.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private List<Employee> actualEmployees;

    private Employee employee1;

    @BeforeEach
    public void setUp(){
        employee1 = new Employee(1,"Petr","Ivanov",40_000,1);
        Employee employee2 = new Employee(1,"Ivan","Petrov",40_000,1);
        Employee employee3 = new Employee(1,"Vika","Loseva",40_000,1);

        actualEmployees = new ArrayList<>(List.of(employee1, employee2, employee3));
        when(employeeRepository.getEmployees()).thenReturn(actualEmployees);
    }

    @Test
    public void ShouldReturnListOfEmployees(){
        Collection<Employee> expected = employeeService.getAllEmployees();
        assertEquals(expected, actualEmployees);
    }

    @Test
    public void ShouldReturnNewEmployee(){
      final Employee actual = employee1;

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setFirstName(actual.getFirstName());
        employeeRequest.setLastName(actual.getLastName());
        employeeRequest.setDepartment(actual.getDepartment());
        employeeRequest.setSalary(actual.getSalary());

        when(employeeRepository.getId()).thenReturn(0);
        Employee expected = employeeService.addEmployee(employeeRequest);
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldReturnSum(){
        final int actual = actualEmployees.stream()
                .mapToInt(Employee::getSalary)
                .sum();
        final int expected = employeeService.getSalarySum();
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldReturnEmployeeWithMinSalary(){
        final Employee actual = actualEmployees.stream()
                .min(Comparator.comparingInt(Employee::getSalary)).get();
        final Employee  expected = employeeService.getSalaryMin();

        assertEquals(expected, actual);
    }

    @Test
    public void ShouldReturnEmployeeWithMaxSalary(){
        final Employee actual = actualEmployees.stream()
                .max(Comparator.comparingInt(Employee::getSalary)).get();
        final Employee  expected = employeeService.getSalaryMax();

        assertEquals(expected, actual);
    }

    @Test
    public void ShouldReturnEmployeeWithSalaryMoreThanAverage(){
        final int average = actualEmployees.stream()
                .mapToInt(Employee::getSalary)
                .sum()
                / actualEmployees.size();

        final List<Employee> expected = actualEmployees.stream()
                .filter(e -> e.getSalary() > average)
                .collect(Collectors.toList());
        final List<Employee> actual = employeeService.getSalaryHigh();

        assertEquals(expected, actual);
    }
}

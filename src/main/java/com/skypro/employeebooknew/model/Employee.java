package com.skypro.employeebooknew.model;

import org.apache.commons.lang3.StringUtils;

public class Employee {
    private static int counter;
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int department;
    private final int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        if(StringUtils.isEmpty(firstName) || StringUtils.isBlank(firstName)){
            throw new IllegalArgumentException();
        } else {
            this.firstName = StringUtils.capitalize(firstName);
        }

        if(StringUtils.isEmpty(lastName) || StringUtils.isBlank(lastName)){
            throw new IllegalArgumentException();
        } else {
            this.lastName = StringUtils.capitalize(lastName);
        }

        this.department = department;
        this.salary = salary;

        this.id = counter++;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}

package com.example.modelmapper.services;

import com.example.modelmapper.entities.Employee;

import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findOneById(int id);

    void save(Employee employee);
}

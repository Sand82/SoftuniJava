package com.example.modelmapper.services;

import com.example.modelmapper.entities.Employee;
import com.example.modelmapper.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> findOneById(int id) {

        return this.employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {

        this.employeeRepository.save(employee);
    }
}

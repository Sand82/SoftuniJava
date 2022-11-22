package com.example.modelmapper.demo;

import com.example.modelmapper.demo.dto.EmployeeDTO;
import com.example.modelmapper.demo.entities.Address;
import com.example.modelmapper.demo.entities.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {
    public static void main(String[] args) {

        ModelMapper mapper = new ModelMapper();

        Address address = new Address("Hristo Botev", 12, "Sofia", "Bulgaria");
        Employee employee = new Employee("Sand", "Stef", BigDecimal.TEN, LocalDate.now(), address);

        EmployeeDTO dto = mapper.map(employee, EmployeeDTO.class);

        System.out.println(dto);
    }
}

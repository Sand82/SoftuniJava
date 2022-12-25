package com.example.hateoas.service;

import com.example.hateoas.models.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    void initializeDatabase();

    StudentDTO getStudent(Long studentsId);

    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();
}

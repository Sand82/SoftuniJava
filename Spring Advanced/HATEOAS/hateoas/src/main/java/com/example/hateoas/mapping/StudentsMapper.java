package com.example.hateoas.mapping;

import com.example.hateoas.models.dto.StudentDTO;
import com.example.hateoas.models.entities.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentsMapper {

    StudentDTO mapEntityToDTO(Student student);
}

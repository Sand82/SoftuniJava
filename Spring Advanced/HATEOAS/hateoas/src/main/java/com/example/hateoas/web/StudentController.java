package com.example.hateoas.web;

import com.example.hateoas.models.dto.StudentDTO;
import com.example.hateoas.service.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudents() {

        List<StudentDTO> studentsDTO = studentService.getAllStudents();

        List<EntityModel<StudentDTO>> models = studentsDTO.stream()
                .map(dto -> EntityModel.of(dto,createStudentsLinks(dto)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentsById(@PathVariable Long studentsId) {

        StudentDTO student = studentService.getStudent(studentsId);

        return ResponseEntity.ok(
                EntityModel.of(student, createStudentsLinks(student))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> update(@PathVariable Long id, StudentDTO studentDTO){

        studentService.updateStudent(id, studentDTO);

        return ResponseEntity.ok().build();
    }

    private Link[] createStudentsLinks(StudentDTO studentDTO) {
        List<Link> result = new ArrayList<>();

        Link selfLink = linkTo(methodOn(StudentController.class)
                .studentService.getStudent(studentDTO.getId())).withSelfRel();
        result.add(selfLink);

        Link updateLink = linkTo(methodOn(StudentController.class)
                .studentService.updateStudent(studentDTO.getId(), studentDTO)).withRel("update");
        result.add(updateLink);

        return result.toArray(result.toArray(new Link[0]));
    }
}

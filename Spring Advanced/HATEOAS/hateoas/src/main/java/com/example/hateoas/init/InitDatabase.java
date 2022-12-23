package com.example.hateoas.init;

import com.example.hateoas.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDatabase implements CommandLineRunner {

    private StudentService studentService;

    public InitDatabase(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {

        studentService.initializeDatabase();
    }
}

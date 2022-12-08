package org.example;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    @Cacheable("students")
    public List<Student> getAllStudents() {

        System.out.println("Doing some hard work");

        return List.of(new Student("Sand", 40), new Student("Mim", 35));
    }
}

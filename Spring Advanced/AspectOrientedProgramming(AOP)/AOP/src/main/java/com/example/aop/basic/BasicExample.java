package com.example.aop.basic;

import com.example.aop.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "examples.basic.enabled", havingValue = "true")
public class BasicExample implements CommandLineRunner {

    private Student student;

    public BasicExample(Student student) {
        this.student = student;
    }

    @Override
    public void run(String... args) throws Exception {
        student.satHello();
        student.echo("from spring!!!");
        student.concat("We arw ", "the best!!!");
        try {
            student.boom();
        }catch (Exception e) {

         }

    }
}

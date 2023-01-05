package com.example.aop.modifying;

import com.example.aop.Student;
import com.example.aop.basic.BasicExampleAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "examples.modifying.enabled", havingValue = "true")
public class ModifyingExample implements CommandLineRunner {
    private Student student;

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyingExample.class);

    public ModifyingExample(Student student) {
        this.student = student;
    }

    @Override
    public void run(String... args) throws Exception {

       String result = student.concat("A", "B");

       LOGGER.info("The result from around advice is: {}", result);
    }
}

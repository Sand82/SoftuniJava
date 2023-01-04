package com.example.events.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Cacheable("students")
    public List<Student> findAllStudents() {

        LOGGER.info("I'm doing some complicated stuff...");

        try {

            Thread.sleep(4000);
        } catch (InterruptedException e) {

            Thread.interrupted();
        }

        LOGGER.info("Complicated calculation finished.");

        return List.of(new Student(1L, "Sand", 40),
                       new Student(2L, "Mish", 7),
                       new Student(3L, "Lub", 2));
    }
}

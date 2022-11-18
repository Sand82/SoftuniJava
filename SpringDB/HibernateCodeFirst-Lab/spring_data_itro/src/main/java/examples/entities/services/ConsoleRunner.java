package examples.entities.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    //private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("asd");
    }
}

package examples.services;


import examples.entities.MajorService;
import examples.entities.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private StudentService studentService;
    private MajorService majorService;

    @Autowired
    public ConsoleRunner(StudentService studentService, MajorService majorService) {

        this.studentService = studentService;
        this.majorService = majorService;
    }

    @Override
    public void run(String... args) throws Exception {

//        this.studentService.register();
//        this.majorService.register();
    }
}

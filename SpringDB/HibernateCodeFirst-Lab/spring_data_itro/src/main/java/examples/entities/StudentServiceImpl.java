package examples.entities;

import examples.entities.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void register() {
        System.out.println("Student service");
    }
}

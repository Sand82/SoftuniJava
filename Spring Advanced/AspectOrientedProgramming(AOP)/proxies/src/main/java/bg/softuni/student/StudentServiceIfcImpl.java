package bg.softuni.student;

import java.util.List;

public class StudentServiceIfcImpl implements StudentServiceIfc {

    @Cacheable("students")
    @Override
    public List<Student> getAllStudents() {

        System.out.println("Calculate students....");

        try{

            Thread.sleep(10000);
        }catch(InterruptedException e){

            Thread.interrupted();
        }

        return List.of(new Student("Sand", 40),
                       new Student("Mim", 36));
    }
}

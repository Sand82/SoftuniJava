package bg.softuni.student;

import java.lang.reflect.Proxy;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentServiceIfc service = proxyObject();

       List<Student> students1 = service.getAllStudents();
       List<Student> students2 = service.getAllStudents();

       students1.forEach(s -> System.out.printf("Name: %s - Age: %d%n", s.getName(), s.getAge()));
       students2.forEach(s -> System.out.printf("Name: %s - Age: %d%n", s.getName(), s.getAge()));
    }

    private static StudentServiceIfc proxyObject(){

        StudentServiceIfcImpl toProxy = new StudentServiceIfcImpl();

        StudentServiceIfc result = (StudentServiceIfc)Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{StudentServiceIfc.class},
                new CacheableInvocationHandler(toProxy)
        );

        return result;
    }
}

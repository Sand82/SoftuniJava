import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Department {

   private String name;

   private double averageSalary;
   private List<Employee> persons = new ArrayList<>();

    public Department(String name,List<Employee> persons) {

        this.name = name;
        this.averageSalary = setAverageSalary();
        this.persons.addAll(persons);
    }

    public double setAverageSalary(){

        double salary = 0.0;

        for (var person: persons) {
            averageSalary += person.getSalary();
        }
        return salary;
    }


    public void addEmployee(Employee employee){
        persons.add(employee);
    }
}

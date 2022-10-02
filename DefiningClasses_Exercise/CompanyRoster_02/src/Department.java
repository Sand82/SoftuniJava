import java.util.ArrayList;
import java.util.List;

public class Department {
   private String name;
   private double averageSalary;
   private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public double setAverageSalary(){

        double salary = 0.0;

        for (var person: employees) {
            averageSalary += person.getSalary();
        }
        return salary;
    }

    public double calculateAverageSalary(){
       return employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }
}

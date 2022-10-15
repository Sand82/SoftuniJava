package bakery;

import java.util.*;

public class Bakery {

    private String name;
    private int capacity;
    private List<Employee> employees;

    private int count;

    public int getCount() {
        return count;
    }

    public Bakery(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        employees = new ArrayList<>();
    }

    public void add(Employee employee) {

        Employee currEmployee = getEmployee(employee.getName());

        if (employees.size() < capacity && currEmployee == null) {
            employees.add(employee);
            count++;
        }

    }

    public Boolean remove(String name) {

        Employee employee = getEmployee(name);

        if (employee == null) {
            return false;
        }
        count--;
        employees.remove(employee);
        return true;
        //employees.removeIf(p -> p.getName().equals(name));
    }

    public Employee getOldestEmployee() {

        Employee employee = employees.stream()
                .max(Comparator.comparingInt(e -> e.getAge()))
                .stream().findFirst().orElse(null);

        return employee;
    }

    public Employee getEmployee(String name) {

        Employee employee = employees.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);

        if (employee == null) {
            return null;
        }

        return employee;
    }

    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Employees working at Bakery {%s}:%n", this.name));

        for (var employee : employees) {
            sb.append(String.format("%s%n", employee.toString()));
        }

        return sb.toString();
    }
}

package cafe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cafe {
    private String name;
    private int capacity;

    private int count;
    private List<Employee> employees = new ArrayList<>();

    public Cafe(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {

        if (employees.size() < capacity) {
            employees.add(employee);
        }
    }

    public int getCount() {
        return employees.size();
    }

    public boolean removeEmployee(String name) {

        Employee employee = getEmployee(name);

        if (employee == null) {
            return false;
        }

        employees.remove(employee);
        return true;
    }

    public Employee getOldestEmployee() {
        return employees.stream().max(Comparator.comparingInt(e -> e.getAge())).stream().findFirst().orElse(null);
    }

    public Employee getEmployee(String name) {
        return employees.stream().filter(e -> e.getName() == name).findFirst().orElse(null);
    }

    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Employees working at Cafe %s:%n", name));

        for (var employee: employees) {
            sb.append(employee);
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}

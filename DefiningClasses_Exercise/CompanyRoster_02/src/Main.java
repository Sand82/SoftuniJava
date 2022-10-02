import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Department> departmentList = new ArrayList<>();

        Map<String, Department> departmentMap = new HashMap<>();

        while (n-- != 0) {

            String[] input = scanner.nextLine().split(" ");

            String name = input[0];
            double salary = Double.parseDouble(input[1]);
            String position = input[2];
            String departmentName = input[3];
            String email;
            int age;

            Employee employee = null;

            if (input.length == 4) {
                employee = new Employee(name, salary, position, departmentName);

            } else if (input.length == 6) {
                email = input[4];
                age = Integer.parseInt(input[5]);

                employee = new Employee(name, salary, position, departmentName, email, age);

            } else {

                if (input[4].contains("@")) {
                    email = input[4];
                    employee = new Employee(name, salary, position, departmentName, email);
                } else {

                    age = Integer.parseInt(input[4]);
                    employee = new Employee(name, salary, position, departmentName, age);
                }
            }

            departmentMap.putIfAbsent(departmentName, new Department(departmentName));
            departmentMap.get(departmentName).getEmployees().add(employee);

            boolean departmentExists = departmentList.stream().filter(d -> d.getName().equals(departmentName)).count() >= 1;

            if (!departmentExists) {
                departmentList.add(new Department(departmentName));
            }
            Department currDepartment = departmentList.stream().filter(d -> d.getName().equals(departmentName)).findFirst().get();
            currDepartment.getEmployees().add(employee);

            departmentList.stream().filter(d -> d.getName().equals(departmentName)).count();
        }

        Department highestPaidDepartment = departmentMap.entrySet().stream()
                .max(Comparator.comparingDouble(e -> e.getValue().calculateAverageSalary()))
                .get()
                .getValue();

        Department highestPaidDepartmentList = departmentList.stream()
                .max(Comparator.comparingDouble(e -> e.calculateAverageSalary()))
                .get();

        System.out.printf("Highest Average Salary: %s%n", highestPaidDepartmentList.getName());

        highestPaidDepartmentList.getEmployees().stream()
                .sorted((a, b) -> (int) (b.getSalary() - a.getSalary())) //Sam 840.20 sam@sam.com -1
                .forEach(x -> System.out.println(x.toString()));
    }
}
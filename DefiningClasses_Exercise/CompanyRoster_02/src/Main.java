import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Department> firmInfo = new ArrayList<>();

        while (n-- != 0) { //Peter 120.00 Dev Development peter@abv.bg 28

            String[] input = scanner.nextLine().split(" ");

            String name = input[0];
            double salary = Double.parseDouble(input[1]);
            String position = input[2];
            String department = input[3];
            String email ="n/a";
            int age = -1;

            Employee employee = null;

            if (input.length == 4) {

            }else if(input.length == 6){
                email = input[4];
                age = Integer.parseInt(input[5]);

            }else {

                if (input[4].contains("@")) {
                    email = input[4];
                }else{
                    age = Integer.parseInt(input[4]);
                }
            }
            employee = new Employee(name, salary, position, department, email, age);

            if (employee == null) {

            }
//
//            Department currDepartment = new Department(name, );
//            currDepartment.addEmployee(employee);
//
//            firmInfo.add(currDepartment);currDepartment

            n--;
        }

       double sal = firmInfo.get(0).setAverageSalary();
        System.out.println(sal);
    }
}
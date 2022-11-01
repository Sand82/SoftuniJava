package prototypePattern;

public class Main {
    public static void main(String[] args) {
        EmployeeRecord person = new EmployeeRecord(1, "Sand", "123456", 1200.00, "Sofia");
        person.showRecord();
        System.out.println("-----------------------------------------------------------------------");
        EmployeeRecord person1 = person.getClone();
        System.out.println(person1.toString());

    }
}

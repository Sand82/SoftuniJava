package SalaryIncrease_02;

import java.text.DecimalFormat;

public class Person {

    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void increaseSalary(double bonus) {

        double currentBonus = salary * bonus / 100;

        if (this.age < 30) {
            currentBonus /= 2;
        }
        salary += currentBonus;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.0###");
        String formated = df.format(this.salary);
        return String.format("%s %s gets %s leva", this.firstName, this.lastName, formated);
    }
}

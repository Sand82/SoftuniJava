package ValidationData_03;

import java.text.DecimalFormat;

public class Person {

    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
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

    public double getSalary() {
        return salary;
    }

    public void setFirstName(String firstName) {

        if (firstName.length() < 3) {
            throw new IllegalArgumentException("First name must be more than 3 symbols");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        if (lastName.length() < 3) {
            throw new IllegalArgumentException("Last name must be more than 3 symbols");
        }
        this.lastName = lastName;
    }

    public void setAge(int age) {

        if (age <= 0 ) {
            throw new IllegalArgumentException("Age must be positive number");
        }
        this.age = age;
    }

    public void setSalary(double salary) {

        if (salary < 460.0 ) {
            throw new IllegalArgumentException("Salary must be more than 460.0 leva.");
        }

        this.salary = salary;
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

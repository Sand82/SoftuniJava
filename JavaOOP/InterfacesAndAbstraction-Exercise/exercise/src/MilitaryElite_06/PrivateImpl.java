package MilitaryElite_06;

public class PrivateImpl extends SoldierImpl implements Private {

    private double salary;

    public PrivateImpl(String firstName, String lastName, int id, double salary) {
        super(firstName, lastName, id);
        this.salary = salary;
    }

    @Override
    public double getSalary() {

        return this.salary;
    }
}

package MilitaryElite_06;

public class Repair {

    private String name;

    private int hoursWorked;

    public Repair(String name, int hoursWorked) {
        this.name = name;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return String.format("Part Name: %s Hours Worked: %d",
                this.name, this.hoursWorked);
    }
}

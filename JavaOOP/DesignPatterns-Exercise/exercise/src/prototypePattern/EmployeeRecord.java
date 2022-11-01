package prototypePattern;

class EmployeeRecord implements Prototype {
    private int id;
    private String name;
    private String designation;
    private double salary;
    private String address;

    public EmployeeRecord() {

    }

    public EmployeeRecord(int id, String name, String designation, double salary, String address) {
        this();
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.address = address;
    }

    public void showRecord() {
        Prototype employeeRecord = getClone();
        System.out.println(employeeRecord.toString());
    }

    @Override
    public EmployeeRecord getClone() {

        return new EmployeeRecord(this.id, this.name, this.designation, this.salary, this.address);
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s, Designation: %s, Salary: %.2f, Address: %s",
                this.id, this.name, this.designation, this.salary, this.address);
    }
}
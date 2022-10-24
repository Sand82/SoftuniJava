package MilitaryElite_06;

public abstract class SoldierImpl implements Solder {
    private String firstName;
    private String lastName;
    private int id;

    public SoldierImpl(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    @Override
    public int getId() {

        return this.id;
    }

    @Override
    public String getFirstName() {

        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;

    }
}

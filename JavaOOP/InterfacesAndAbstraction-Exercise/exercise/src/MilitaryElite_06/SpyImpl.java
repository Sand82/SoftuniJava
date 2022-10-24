package MilitaryElite_06;

public class SpyImpl extends SoldierImpl implements Spy, Solder {

    private String codeNumber;

    public SpyImpl(String firstName, String lastName, int id, String codeNumber) {
        super(firstName, lastName, id);

        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "Code Number: " + this.codeNumber;
    }
}

package MilitaryElite_06;

public class SpyImpl extends SoldierImpl implements Spy, Solder {

    private String codeNumber;

    public SpyImpl(String firstName, String lastName, int id, String codeNumber) {
        super(firstName, lastName, id);

        this.codeNumber = codeNumber;
    }
}

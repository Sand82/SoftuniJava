package MilitaryElite_06;

import java.util.Arrays;
import java.util.List;

public abstract class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier, Solder {
    private Corps corps;
    private List<String> corpses = Arrays.asList("AIRFORCES", "MARINES");

    public SpecialisedSoldierImpl(String firstName, String lastName, int id, double salary, Corps corps) {
        super(firstName, lastName, id, salary);

        this.corps = corps;
    }

    @Override
    public Enum<Corps> getCorps() {

        return this.getCorps();
    }

    public void setCorps(Corps corps) {

        if (!corpses.contains(corps.name().toUpperCase())) {

            throw new IllegalArgumentException("This type of corps " + corps.name()+" don't exist." );
        }

        this.corps = corps;
    }
}

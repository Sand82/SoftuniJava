package MilitaryElite_06;

import java.util.ArrayList;
import java.util.Collection;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer, Solder {
    private Collection<Repair> repairs;

    public EngineerImpl(String firstName, String lastName, int id, double salary, Corps corps) {
        super(firstName, lastName, id, salary, corps);

        this.repairs = new ArrayList<>();
    }

    @Override
    public void addRepair(Repair repair) {
        repairs.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(super.toString()).append(System.lineSeparator());
        sb.append("Corps: " + this.getCorps()).append(System.lineSeparator());
        sb.append("Repairs:").append(System.lineSeparator());

        for (Repair repair : repairs) {
            sb.append("  " + repair.toString()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}

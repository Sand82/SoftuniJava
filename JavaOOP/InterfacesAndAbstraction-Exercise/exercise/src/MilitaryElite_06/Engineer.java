package MilitaryElite_06;

import java.util.Collection;

public interface Engineer {

    void addRepair(Repair repair);

    Collection<Repair> getRepairs();
}

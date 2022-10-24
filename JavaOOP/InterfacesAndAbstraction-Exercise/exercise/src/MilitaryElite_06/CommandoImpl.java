package MilitaryElite_06;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando, Solder {

    private Map<String, Mission> missions;

    public CommandoImpl(String firstName, String lastName, int id, double salary, Corps corps) {
        super(firstName, lastName, id, salary, corps);
        this.missions = new LinkedHashMap<>();
    }

    @Override
    public void addMission(Mission mission) {
        this.missions.putIfAbsent(mission.getCodeName(), mission);
    }

    @Override
    public Map<String, Mission> getMissions() {
        return this.missions;
    }
}

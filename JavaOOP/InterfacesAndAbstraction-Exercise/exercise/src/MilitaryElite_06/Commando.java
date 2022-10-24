package MilitaryElite_06;

import java.util.Map;

public interface Commando {

    void addMission(Mission mission);

    Map<String, Mission> getMissions();
}

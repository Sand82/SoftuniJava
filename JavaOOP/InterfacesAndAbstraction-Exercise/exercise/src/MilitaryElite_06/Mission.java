package MilitaryElite_06;

public class Mission {

    private Enum<State> states;

    private String codeName;

    public Mission(Enum<State> states, String codeName) {
        this.states = states;
        this.codeName = codeName;
    }

    public Enum<State> getStates() {

        return states;
    }

    public String getCodeName() {

        return codeName;
    }

    public void completeMission() {

        this.states = State.FINISHED;
    }
}

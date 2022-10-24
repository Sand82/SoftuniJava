package MilitaryElite_06;
public class Mission {
    private State states;
    private String codeName;

    public Mission(State states, String codeName) {

        this.states = states;
        this.codeName = codeName;
    }

    public State getStates() {

        return states;
    }

    public String getCodeName() {

        return codeName;
    }

    public void completeMission() {

        this.states = State.finished;
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s",
                this.codeName, this.states.name() );
    }
}

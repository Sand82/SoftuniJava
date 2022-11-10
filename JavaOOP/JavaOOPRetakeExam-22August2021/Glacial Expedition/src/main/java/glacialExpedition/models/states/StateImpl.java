package glacialExpedition.models.states;

import java.util.ArrayList;
import java.util.Collection;

import static glacialExpedition.common.ExceptionMessages.STATE_NAME_NULL_OR_EMPTY;

public class StateImpl implements State {

    private String name;
    private Collection<String> exhibits;


    public StateImpl(String name) {

        this.setName(name);

        this.exhibits = new ArrayList<>();
    }

    @Override
    public Collection<String> getExhibits() {

        return this.exhibits;
    }

    @Override
    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {

            throw new NullPointerException(STATE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setExhibits(Collection<String> exhibits) {
        this.exhibits = exhibits;
    }
}

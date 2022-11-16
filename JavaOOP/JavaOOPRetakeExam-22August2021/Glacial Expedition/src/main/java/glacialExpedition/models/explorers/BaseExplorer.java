package glacialExpedition.models.explorers;

import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import static glacialExpedition.common.ExceptionMessages.*;

public abstract class BaseExplorer implements Explorer {

    private String name;
    private double energy;
    private Suitcase suitcase;

    public BaseExplorer(String name, double energy) {

        this.setName(name);
        this.setEnergy(energy);

        this.suitcase = new Carton();
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public double getEnergy() {

        return this.energy;
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {

            throw new NullPointerException(EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergy(double energy) {

        if (energy < 0) {

            throw new IllegalArgumentException(EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public boolean canSearch() {

        return (energy > 0);
    }

    @Override
    public Suitcase getSuitcase() {

        return this.suitcase;
    }

    @Override
    public void search() {

        if (getEnergy() - 15 < 0) {

            setEnergy(0);
        } else {

            setEnergy(getEnergy() - 15);
        }
    }
}

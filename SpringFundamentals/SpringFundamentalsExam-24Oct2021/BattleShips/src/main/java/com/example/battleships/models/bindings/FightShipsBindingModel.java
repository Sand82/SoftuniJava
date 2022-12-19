package com.example.battleships.models.bindings;

public class FightShipsBindingModel {

    private String attacker;

    private String defender;

    public FightShipsBindingModel() {
    }

    public String getAttacker() {
        return attacker;
    }

    public FightShipsBindingModel setAttacker(String attacker) {
        this.attacker = attacker;
        return this;
    }

    public String getDefender() {
        return defender;
    }

    public FightShipsBindingModel setDefender(String defender) {
        this.defender = defender;
        return this;
    }
}

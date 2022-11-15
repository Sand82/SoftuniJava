package football.entities.field;

import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static football.common.ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY;

public abstract class BaseField implements Field {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {

        this.setName(name);
        this.capacity = capacity;

        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {

            throw new NullPointerException(FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public int sumEnergy() {
        return supplements.stream().mapToInt(Supplement::getEnergy).sum();
    }

    @Override
    public void addPlayer(Player player) {

        if (players.size() >= capacity) {

            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }

        players.add(player);
    }

    @Override
    public void removePlayer(Player player) {

        players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {

        supplements.add(supplement);
    }

    @Override
    public void drag() {

        players.forEach(Player::stimulation);
    }

    @Override
    public String getInfo() {

        String playerInfo = players.size() > 0
                ? this.players.stream().map(Player::getName).collect(Collectors.joining(" "))
                : "none";

        int supplementCount = supplements.size();
        int sumEnergy = sumEnergy();

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Player: %s", playerInfo));
        sb.append(System.lineSeparator());
        sb.append(String.format("Supplement: %d", supplementCount));
        sb.append(System.lineSeparator());
        sb.append(String.format("Energy: %d", sumEnergy));

        return sb.toString();
    }
}

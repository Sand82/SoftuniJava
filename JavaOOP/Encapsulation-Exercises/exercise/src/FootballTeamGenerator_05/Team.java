package FootballTeamGenerator_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {

        this.setName(name);

        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void setName(String name) {

        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }

        this.name = name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(String player) {

        boolean isRemove = players.removeIf(p -> p.getName().equals(player));

        if (!isRemove) {
            throw new IllegalArgumentException(String.format(
                    "Player %s is not in %s team.", player, this.name));
        }
    }

    public double getRating() {

        return players.stream()
                .mapToDouble(Player::overallSkillLevel)
                .average().orElse(0.0);
    }
}

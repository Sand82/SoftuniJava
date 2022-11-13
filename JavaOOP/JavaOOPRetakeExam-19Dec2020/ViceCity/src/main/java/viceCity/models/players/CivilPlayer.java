package viceCity.models.players;

public class CivilPlayer extends BasePlayer {

    private static final int LIFEPOINTS = 50;

    public CivilPlayer(String name) {
        super(name, LIFEPOINTS);
    }
}

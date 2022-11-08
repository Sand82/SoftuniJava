package football.entities.player;

public class Men extends BasePlayer {

    private static final double KILOGRAMS = 85.50;
    private static final int INCREASE_POINTS = 145;

    public static final String playGround = "NaturalGrass";

    public Men(String name, String nationality, int strength) {

        super(name, nationality, KILOGRAMS, strength);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + INCREASE_POINTS);
    }
}

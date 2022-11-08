package football.entities.player;

public class Women extends BasePlayer {

    private static final double KILOGRAMS = 60.00;
    private static final int INCREASE_POINTS = 115;

    public static final String playGround = "ArtificialTurf";

    public Women(String name, String nationality, int strength) {

        super(name, nationality, KILOGRAMS, strength);
    }

    @Override
    public void stimulation() {

        setStrength(getStrength() + INCREASE_POINTS);
    }
}

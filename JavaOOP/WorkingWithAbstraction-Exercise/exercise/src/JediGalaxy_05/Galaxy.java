package JediGalaxy_05;

public class Galaxy {

    private Field field;

    private EvilMovement evilMovement = new EvilMovement();
    private JadiMovement jadiMovement = new JadiMovement();
    public Galaxy(Field field) {
        this.field = field;
    }

    public void moveEvil(int row, int col) {
        evilMovement.evilMove(row, col, this.field);
    }

    public long moveJadi(int row, int col) {

        return jadiMovement.move(row, col, field);
    }

}

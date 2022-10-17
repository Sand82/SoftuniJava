package JediGalaxy_05;

public class JadiMovement {

    public long move(int row, int col, Field field) {
        long starsCollected = 0;

        while (row >= 0 && col < field.getColLength(1)) {
            if (field.isInBound(row, col)) {
                starsCollected += field.collectStarts(row, col);
            }

            col++;
            row--;
        }
        return starsCollected;
    }
}

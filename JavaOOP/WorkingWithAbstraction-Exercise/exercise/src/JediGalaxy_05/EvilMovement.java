package JediGalaxy_05;

import java.io.File;

public class EvilMovement {

    public void evilMove(int row, int col, Field field) {
        while (row >= 0 && col >= 0) {
            if (field.isInBound(row, col)) {
                field.setValue(row, col, 0);
            }
            row--;
            col--;
        }
    }
}

package JediGalaxy_05;

public class Field {

    private int [][] matrix;

    public Field(int rows, int cols) {
        this(rows, cols, 0);
    }

    public Field(int rows, int cols, int beginValue) {
        this.matrix = new int[rows][cols];
        this.fillValues(beginValue);
    }

    private void fillValues(int beginValue) {

        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < this.matrix[row].length; col++) {
                matrix[row][col] = beginValue++;
            }
        }
    }

    public boolean isInBound(int row, int col) {
        return row >= 0 && row < this.matrix.length && col >= 0 && col < this.matrix[row].length;
    }

    public void setValue(int row, int col, int newValue) {
        matrix[row][col] = newValue;
    }

    public int getColLength(int row) {
        return matrix[row].length;
    }

    public int collectStarts(int row, int col) {
        return matrix[row][col];
    }
}

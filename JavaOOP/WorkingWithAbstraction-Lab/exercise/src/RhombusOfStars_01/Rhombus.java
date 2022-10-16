package RhombusOfStars_01;

public class Rhombus {

    private int n;

    public Rhombus(int n) {
        this.n = n;
    }

    public String getFigure() {

        return getTop() + getMiddle() + getBottom();
    }

    private String getTop() {

        StringBuilder sb = new StringBuilder();

        for (int r = 1; r < n; r++) {

            sb.append(repeatAndPrintString(n - r, " "))
                    .append(repeatAndPrintString(r, "* "))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

    private String getMiddle() {
        StringBuilder sb = new StringBuilder();

        sb.append(repeatAndPrintString(n, "* "))
                .append(System.lineSeparator());

        return sb.toString();
    }

    private String getBottom() {

        StringBuilder sb = new StringBuilder();

        for (int r = 1; r < n; r++) {

            sb.append(repeatAndPrintString(r, " "))
                    .append(repeatAndPrintString(n - r, "* "))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

    private String repeatAndPrintString(int count, String str) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(str);
        }

        return sb.toString();
    }
}

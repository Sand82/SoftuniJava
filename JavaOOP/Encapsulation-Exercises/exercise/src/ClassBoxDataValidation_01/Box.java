package ClassBoxDataValidation_01;

public class Box {


    private double length;

    private double width;

    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setLength(double length) {

        validateParameters(length, "length");

        this.length = length;
    }

    public void setWidth(double width) {

        validateParameters(width, "Width");

        this.width = width;
    }

    public void setHeight(double height) {

        validateParameters(height, "Height");

        this.height = height;
    }

    private void validateParameters(double parameter, String parameterName) {

        if (parameter <= 0) {
            throw new IllegalArgumentException(parameterName + " cannot be zero or negative.");
        }
    }

    public double calculateSurfaceArea() { //2lw + 2lh + 2wh
        return 2 * ((length * width) + (length * height) + (width * height));
    }

    public double calculateLateralSurfaceArea() { //2lh + 2wh
        return (2 * length * height) + (2 * width * height);
    }

    public double calculateVolume() {
        return length * height * width;
    }
}

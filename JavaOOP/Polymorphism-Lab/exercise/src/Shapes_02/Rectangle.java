package Shapes_02;

public class Rectangle extends Shape{

    private final double width;
    private final double height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculatePerimeter() {

        if (perimeter == null) {

            perimeter = 2.0 * width + 2.0 * height;
        }

        return perimeter;
    }

    @Override
    public double calculateArea() {

        if (area == null) {
            area = this.width * this.height;
        }

        return area;
    }
}

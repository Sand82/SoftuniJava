package Shapes_02;

public class Circle extends Shape{

    private final Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    @Override
    public double calculatePerimeter() {

        if (perimeter == null) {

           perimeter = Math.PI * 2 * radius;
        }
        return perimeter;
    }

    @Override
    public double calculateArea() {

        if (area == null) {
            area = Math.PI * radius * radius;
        }
        return area;
    }
}

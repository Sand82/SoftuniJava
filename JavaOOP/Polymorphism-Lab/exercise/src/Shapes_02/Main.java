package Shapes_02;

public class Main {
    public static void main(String[] args) {
        Shape shape = new Circle(4.0);

        System.out.println(shape.calculatePerimeter());
        System.out.println(shape.calculateArea());
    }
}

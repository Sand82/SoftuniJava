package PointInRectangle_02;

public class Rectangle {
    private Point pointA;

    private Point pointC;

    public Rectangle(Point a, Point c) {
        pointA = a;
        pointC = c;
    }

    public boolean contains(Point point) {
        return point.getX() >= pointA.getX() &&
                point.getY() >= pointA.getY() &&
                point.getX() <= pointC.getX() &&
                point.getY() <= pointC.getY();
    }
}

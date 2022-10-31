package exercise;

// BEGIN
public class Circle {
    private Point centerPoint;
    private int radius;

    public Circle(Point centerPoint, int radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Error! Radius of circle cannot be negative");
        }

        return Math.PI * radius * radius;
    }


}
// END

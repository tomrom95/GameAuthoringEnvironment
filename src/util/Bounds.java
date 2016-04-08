package util;

/**
 * Class to represent rectangular bounds for collision checking
 * 
 * @author RyanStPierre
 *
 */
public class Bounds {

    private static final double HALF = 0.5;
    private double myX;
    private double myY;
    private double myWidth;
    private double myHeight;

    public Bounds (double x, double y, double width, double height) {
        myX = x;
        myY = y;
        myWidth = width;
        myHeight = height;
    }

    public boolean collide (Bounds other) {
        if (other.getLeft() > getRight() || getLeft() > other.getRight()) {
            return false;
        }
        else if (other.getTop() > getBottom() || getTop() > other.getBottom()) {
            return false;
        }

        return true;
    }

    public double getLeft () {
        return myX - half(myWidth);
    }

    public double getTop () {
        return myY - half(myHeight);
    }

    public double getBottom () {
        return myY + half(myHeight);
    }

    public double getRight () {
        return myX + half(myWidth);
    }

    private double half (double input) {
        return input * HALF;
    }

    public boolean contains (Coordinate coordinate) {
        return coordinate.getX() >= getLeft() && coordinate.getX() <= getRight() 
                && coordinate.getY() <= getBottom() && coordinate.getY() >= getTop();
    }

}

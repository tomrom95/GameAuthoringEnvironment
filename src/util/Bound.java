package util;

/**
 * Class to represent rectangular bounds for collision checking
 * 
 * @author RyanStPierre
 *
 */
public class Bound {

    private static final double HALF = 0.5;
    private double myX;
    private double myY;
    private double myWidth;
    private double myHeight;

    public Bound (double x, double y, double width, double height) {
        myX = x;
        myY = y;
        myWidth = width;
        myHeight = height;
    }

    public boolean collide (Bound other) {
        if (other.getLeft() > getRight() || getLeft() > other.getRight()) {
            return false;
        }
        else if (other.getTop() > getButtom() || getTop() > other.getButtom()) {
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

    public double getButtom () {
        return myY + half(myHeight);
    }

    public double getRight () {
        return myX + half(myWidth);
    }

    private double half (double input) {
        return input * HALF;
    }

}

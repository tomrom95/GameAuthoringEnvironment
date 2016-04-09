package util;

/**
 * Class to represent rectangular bounds for collision checking
 * 
 * @author RyanStPierre
 *
 */
public class Bounds {

   
    
    /**
     * myX and myY refer to TOP-LEFT of bounds
     */
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
        return myX;
    }

    public double getTop () {
        return myY;
    }

    public double getBottom () {
        return myY + myHeight;
    }

    public double getRight () {
        return myX + myWidth;
    }


    public boolean contains (Coordinate coordinate) {
        return coordinate.getX() >= getLeft() && coordinate.getX() <= getRight() 
                && coordinate.getY() <= getBottom() && coordinate.getY() >= getTop();
    }

}

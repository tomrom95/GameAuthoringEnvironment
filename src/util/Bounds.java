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
    
    /**
     * 
     * @param x - center x
     * @param y - center y
     * @param width
     * @param height
     */

    public Bounds (double x, double y, double width, double height) {
        myX = x - width/2;
        myY = y - height/2;
        myWidth = width;
        myHeight = height;
    }
    
    public Bounds (double width, double height) {
        myX = 0;
        myY = 0;
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
    
    public double getHeight () {
        return myHeight;
    }
    
    public double getWidth (){
        return myWidth;
    }


    public boolean contains (Coordinate coordinate) {
        return coordinate.getX() >= getLeft() && coordinate.getX() <= getRight() 
                && coordinate.getY() <= getBottom() && coordinate.getY() >= getTop();
    }

}

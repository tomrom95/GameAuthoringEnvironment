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

    /**
     * Similar to {@link #contains(Coordinate)} but inclusive points 1 away
     * from the actual edge.
     * @param coordinate
     * @return
     */
    public boolean edgeBoundsContains (Coordinate coordinate) {
        return coordinate.getX() >= getLeft() - 1 && coordinate.getX() <= getRight() + 1 
                && coordinate.getY() <= getBottom() - 1  && coordinate.getY() >= getTop() + 1;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(myHeight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(myWidth);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(myX);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(myY);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Bounds other = (Bounds) obj;
        if (Double.doubleToLongBits(myHeight) != Double.doubleToLongBits(other.myHeight)) {
            return false;
        }
        if (Double.doubleToLongBits(myWidth) != Double.doubleToLongBits(other.myWidth)) {
            return false;
        }
        if (Double.doubleToLongBits(myX) != Double.doubleToLongBits(other.myX)) {
            return false;
        }
        if (Double.doubleToLongBits(myY) != Double.doubleToLongBits(other.myY)) {
            return false;
        }
        return true;
    }

    public double getMyWidth () {
        return myWidth;
    }

    public double getMyHeight () {
        return myHeight;
    }

}

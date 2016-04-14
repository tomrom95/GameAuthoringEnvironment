package util;

import java.io.Serializable;


/**
 * Utility class that stores two double values as a coordinate and manipulates them.
 * 
 * @author Dhrumil Timko
 *
 */

public class Coordinate {
    private final static String FORMATTER = "(%.2f,%.2f)";

    private double myXCoordinate;
    private double myYCoordinate;

    public Coordinate (double xCor, double yCor) {
        setX(xCor);
        setY(yCor);
    }
    
    public double getX () {
        return myXCoordinate;
    }

    private void setX (double xCor) {
        myXCoordinate = xCor;
    }

    public double getY () {
        return myYCoordinate;
    }

    private void setY (double yCor) {
        myYCoordinate = yCor;
    }
    
    @Override
    public String toString () {
        return String.format(FORMATTER, getX(), getY());
    }
   
}

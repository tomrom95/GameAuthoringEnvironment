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

    private double xCoordinate;
    private double yCoordinate;
    private double orientation;

    public Coordinate (double xCor, double yCor) {
        setX(xCor);
        setY(yCor);
        setOrientation(0);
    }
    
    public Coordinate (double xCor, double yCor, double heading){
        setX(xCor);
        setY(yCor);
        setOrientation(heading);
    }


    
    public double getX () {
        return xCoordinate;
    }

    private void setX (double xCor) {
        xCoordinate = xCor;
    }

    public double getY () {
        return yCoordinate;
    }

    private void setY (double yCor) {
        yCoordinate = yCor;
    }

    public void setLocation (double x, double y) {
        setX(x);
        setY(y);

    }
    
    public String toString () {
        return String.format(FORMATTER, getX(), getY());
    }
    
    /*
     * changes to support orientation below
     * getter, private setter, overloaded constructor
     */
    
    public double getOrientation(){
        return orientation;
    }
    
    private void setOrientation(double heading){
        while(heading >= 360){
            heading = heading - 360;
        }
        
        orientation = heading;
    }
}

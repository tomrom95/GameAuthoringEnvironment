package util;


/**
 * Utility class that stores two double values as a coordinate and manipulates them.
 * 
 * @author Dhrumil Timko
 *
 */

public class Coordinate {
    private static final  String FORMATTER = "(%.2f,%.2f)";

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

    public static double distance (Coordinate first, Coordinate second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) -
                         Math.pow(first.getY() - second.getY(), 2));
    }

}

package util;

/**
 * Utility class that stores two double values as a coordinate and manipulates them.
 * 
 * @author Dhrumil Timko
 *
 */

public class Coordinate {
    private static final String FORMATTER = "(%.2f,%.2f)";

    private double myXCoordinate;
    private double myYCoordinate;

    public Coordinate (double xCor, double yCor) {
        setX(xCor);
        setY(yCor);
    }
    
    public Coordinate (ArrayPosition pos) {
        setX(pos.getX());
        setY(pos.getY());
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
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) +
                         Math.pow(first.getY() - second.getY(), 2));
    }

    @Override
    public int hashCode () {
        final int PRIME = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(myXCoordinate);
        result = PRIME * result + (int)
                (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(myYCoordinate);
        result = PRIME * result + (int)
                (temp ^ (temp >>> 32));
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
        Coordinate other = (Coordinate) 
                obj;
        if (Double.doubleToLongBits(myXCoordinate) != Double
                .doubleToLongBits(other.myXCoordinate)) {
            return false;
        }
        if (Double.doubleToLongBits(myYCoordinate) != Double
                .doubleToLongBits(other.myYCoordinate)) {
            return false;
        }
        return true;
    }

}

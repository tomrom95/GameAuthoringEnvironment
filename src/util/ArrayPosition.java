package util;

/**
 * Data class to assist in array operations in the ai/pathing classes
 * 
 * @author jonathanim
 *
 */
public class ArrayPosition {

    private int myX;
    private int myY;

    public ArrayPosition (Coordinate coord) {
        this((int) coord.getX(), (int) coord.getY());
    }

    public ArrayPosition (ArrayPosition pos) {
        this(pos.getX(), pos.getY());
    }

    public ArrayPosition () {
        this(0, 0);
    }

    public ArrayPosition (int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX () {
        return myX;
    }

    public int getY () {
        return myY;
    }

    public void setX (int x) {
        myX = x;
    }

    public void setY (int y) {
        myY = y;
    }

    @Override
    public int hashCode () {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + myX;
        result = PRIME * result + myY;
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
        ArrayPosition other = (ArrayPosition) obj;
        if (myX != other.myX) {
            return false;
        }
        if (myY != other.myY) {
            return false;
        }
        return true;
    }

}

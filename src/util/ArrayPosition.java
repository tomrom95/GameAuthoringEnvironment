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

}

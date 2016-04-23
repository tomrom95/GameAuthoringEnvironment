package util;

import java.util.Iterator;


/**
 * Object representation of Bitmap. It will be used for implementing placeable-area, obstruction,
 * and artificial intelligence Pathing.
 * 
 * @author Jin An
 * @author Jon Im
 *
 */
public class BitMap implements Iterable<Boolean> {

    private boolean[][] myBitMap;
    private int myWidth;
    private int myHeight;

    public BitMap (int width, int height) {
        initialize(width, height);
    }

    public BitMap (BitMap map) {
        initialize(map.getWidth(), map.getHeight());
        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                this.set(i, j, map.valueOf(i, j));
            }
        }
    }

    public BitMap () {
        this(1, 1);
    }

    /**
     * Should use constructors and overloaded constructors instead of having
     * methods for post initialization
     * Please replace usage with {@link #BitMap()} or {@link #BitMap(int, int) BitMap(int width, int
     * height)}
     * 
     * @param width
     * @param height
     */
    @Deprecated
    public void createBitMap (int width, int height) {
        initialize(width, height);
    }

    private void initialize (int width, int height) {
        myBitMap = new boolean[width][height];
        myWidth = width;
        myHeight = height;
    }

    public int getHeight () {
        return myHeight;
    }

    public int getWidth () {
        return myWidth;
    }

    public void flip (int row, int column) {
        myBitMap[row][column] = !(myBitMap[row][column]);
    }

    /**
     * This method will return false for any requests that fall outside the bounds
     * of the stored array
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean valueOf (int row, int column) {
        if (row >= getWidth() || column >= getHeight() || row < 0 || column < 0) {
            return false;
        }
        return myBitMap[row][column];
    }

    public boolean valueOf (Coordinate coord) {
        return valueOf((int)
                       coord.getX(), (int) 
                       coord.getY());
    }

    public void set (int row, int column, boolean value) {
        myBitMap[row][column] = value;
    }

    public boolean[][] getBitMap () {
        return myBitMap;
    }

    @Override
    public Iterator<Boolean> iterator () {
        return new Iterator<Boolean>() {
            private final int myMaxValue = getHeight() * getWidth() - 1;
            private int myCurLoc = -1;

            @Override
            public boolean hasNext () {
                return myCurLoc < myMaxValue;
            }

            @Override
            public Boolean next () {
                myCurLoc++;
                return new Boolean(getBitMap()[myCurLoc / getHeight()][myCurLoc % getHeight()]);
            }

        };

    }

}

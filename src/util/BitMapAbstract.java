package util;

import java.util.Iterator;

public abstract class BitMapAbstract implements IBitMap, Iterable<Boolean> {
    
    private boolean[][] myBitMap;
    private int myWidth;
    private int myHeight;
    
    public BitMapAbstract (int width, int height) {
        initialize(width, height);
    }

    public BitMapAbstract (IBitMap map) {
        initialize(map.getWidth(), map.getHeight());
        //myBitMap = map.getBitMap().clone();
        Iterator<ArrayPosition> iter = map.positionIterator();
        while (iter.hasNext()) {
            ArrayPosition pos = iter.next();
            this.set(pos, map.valueOf(pos));
        }
    }

    public BitMapAbstract () {
        this(1, 1);
    }

    /**
     * @deprecated
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

    protected void initialize (int width, int height) {
        myBitMap = new boolean[width][height];
        myWidth = width;
        myHeight = height;
    }
    

    @Override
    public int getHeight () {
        return myHeight;
    }

    @Override
    public int getWidth () {
        return myWidth;
    }

    @Override
    public void flip (int row, int column) {
        myBitMap[row][column] = !(myBitMap[row][column]);
    }



    @Override
    public boolean valueOf (ArrayPosition pos) {
        return valueOf(pos.getX(), pos.getY());
    }

    @Override
    public boolean valueOf (Coordinate coord) {
        return valueOf((int)
                       coord.getX(), (int) 
                       coord.getY());
    }

    @Override
    public void set (int row, int column, boolean value) {
        myBitMap[row][column] = value;
    }

    @Override
    public void set (ArrayPosition pos, boolean value) {
        set(pos.getX(), pos.getY(), value);
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

    @Override
    public Iterator<ArrayPosition> positionIterator () {
        return new Iterator<ArrayPosition>() {
            private final int myMaxValue = getHeight() * getWidth() - 1;
            private int myCurLoc = -1;

            @Override
            public boolean hasNext () {
                return myCurLoc < myMaxValue;
            }

            @Override
            public ArrayPosition next () {
                myCurLoc++;
                return new ArrayPosition(myCurLoc / getHeight(), myCurLoc % getHeight());
            }
        };
    }

    @Override
    public boolean[][] getBitMap () {
        return myBitMap;
    }

    @Override
    public boolean inBounds (int x, int y) {
        return (x >= 0) && (x < getWidth()) && (y >= 0) && (y < getHeight());
    }

    @Override
    public boolean inBounds (ArrayPosition pos) {
        return inBounds(pos.getX(), pos.getY());
    }
    


}

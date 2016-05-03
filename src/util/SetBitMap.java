package util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class SetBitMap implements IBitMap {
    private int myHeight;
    private int myWidth;
    private Set<ArrayPosition> myTrue;

    public SetBitMap (int height, int width) {
        setHeight(height);
        setWidth(width);
        setTrue(new HashSet<>());
    }

    @Override
    public int getHeight () {
        return myHeight;
    }

    @Override
    public int getWidth () {
        return myWidth;
    }

    private void setHeight (int height) {
        myHeight = height;
    }

    private void setWidth (int width) {
        myWidth = width;
    }

    @Override
    public void flip (int row, int column) {
        ArrayPosition pos = posForInt(row, column);
        if (getTrueSet().contains(pos)) {
            getTrueSet().remove(pos);
        }
        else {
            getTrueSet().add(pos);
        }

    }

    @Override
    public boolean valueOf (int row, int column) {
        return valueOf(posForInt(row, column));
    }

    @Override
    public boolean valueOf (ArrayPosition pos) {
        return !inBounds(pos) ? true : getTrueSet().contains(pos);
    }

    @Override
    public boolean valueOf (Coordinate coord) {
        ArrayPosition toCheck = new ArrayPosition((int) coord.getX(), (int) coord.getY());
        return valueOf(toCheck);
    }

    @Override
    public void set (int row, int column, boolean value) {
        set(posForInt(row, column), value);
    }

    @Override
    public void set (ArrayPosition pos, boolean value) {
        if (value && inBounds(pos)) {
            getTrueSet().add(pos);
        }
        else {
            getTrueSet().remove(pos);
        }

    }

    @Override
    public Iterator<Boolean> iterator () {
        return new Iterator<Boolean>() {
            private int numPos = getWidth() * getHeight() - 1;
            private int curPos = 0;

            @Override
            public boolean hasNext () {
                return curPos < numPos;
            }

            @Override
            public Boolean next () {
                curPos++;
                return valueOf(curPos / getHeight(), curPos % getHeight());
            }

        };
    }

    @Override
    public Iterator<ArrayPosition> positionIterator () {
        return new Iterator<ArrayPosition>() {
            private int numPos = getWidth() * getHeight() - 1;
            private int curPos = 0;

            @Override
            public boolean hasNext () {
                return curPos < numPos;
            }

            @Override
            public ArrayPosition next () {
                curPos++;
                return posForInt(curPos / getHeight(), curPos % getHeight());
            }

        };
    }

    @Override
    public boolean[][] getBitMap () {
        boolean[][] toReturn = new boolean[getWidth()][getHeight()];
        Iterator<ArrayPosition> posIter = getTrueSet().iterator();
        while (posIter.hasNext()) {
            ArrayPosition next = posIter.next();
            toReturn[next.getX()][next.getY()] = true;
        }
        return toReturn;
    }

    @Override
    public boolean inBounds (int x, int y) {
        return (x >= 0) && (x <= getWidth()) && (y >= 0) && (y <= getHeight());
    }

    @Override
    public boolean inBounds (ArrayPosition pos) {
        return inBounds(pos.getX(), pos.getY());
    }

    private Set<ArrayPosition> getTrueSet () {
        return myTrue;
    }

    private void setTrue (Set<ArrayPosition> set) {
        myTrue = set;
    }

    private ArrayPosition posForInt (int i, int j) {
        return new ArrayPosition(i, j);
    }

    protected Iterator<ArrayPosition> trueIter () {
        return getTrueSet().iterator();
    }

}

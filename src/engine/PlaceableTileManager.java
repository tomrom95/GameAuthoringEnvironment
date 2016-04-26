package engine;

import util.BitMap;


public class PlaceableTileManager implements IPlaceableTileManager {

    private ILevel myLevel;
    private BitMap myPlaceableMap;

    private int myRowSize;
    private int myColumnSize;

    public PlaceableTileManager (ILevel level) {
        myLevel = level;
    }

    public void set (int row, int column) {
        myPlaceableMap = new BitMap(row, column);
        myRowSize = row;
        myColumnSize = column;
    }

    public int getRowSize () {
        return myRowSize;
    }

    public int getColumnSize () {
        return myColumnSize;
    }

    public ILevel getLevel () {
        return myLevel;
    }

    @Override
    public BitMap getPlaceableMap () {
        return myPlaceableMap;
    }
}

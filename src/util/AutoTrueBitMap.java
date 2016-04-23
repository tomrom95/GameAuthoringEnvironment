package util;

public class AutoTrueBitMap extends BitMap {
    
    public AutoTrueBitMap (int width, int height) {
        super(width, height);
    }

    public AutoTrueBitMap () {
        this(1, 1);
    }

    public AutoTrueBitMap (IBitMap map) {
        super(map);
    }

    @Override
    /**
     * This method will return true for any requests that fall outside the bounds
     * of the stored array
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean valueOf (int row, int column) {
        if (row >= getWidth() || column >= getHeight() || row < 0 || column < 0) {
            return true;
        }
        return getBitMap()[row][column];
    }
}

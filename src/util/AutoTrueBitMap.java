package util;

public class AutoTrueBitMap extends BitMapAbstract {

    public AutoTrueBitMap (int width, int height) {
        super(width, height);
    }

    public AutoTrueBitMap () {
        this(1, 1);
    }

    public AutoTrueBitMap (IBitMap map) {
        super(map);
    }

    /**
     * This method will return true for any requests that fall outside the bounds
     * of the stored array
     *
     * @param row
     * @param column
     * @return
     */

    @Override
    public boolean valueOf (int row, int column) {
        if (!inBounds(row, column)) {
            return true;
        }
        return getBitMap()[row][column];
    }

    @Override
    public void set (int row, int column, boolean value) {
        if (inBounds(row, column)) {
            getBitMap()[row][column] = value;
        }
    }
}

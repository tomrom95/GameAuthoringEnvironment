package util;

/**
 * Object representation of Bitmap. It will be used for implementing placeable-area, obstruction,
 * and artificial intelligence Pathing.
 * 
 * @author Jin An
 * @author Jon Imm
 *
 */
public class BitMap {

    private boolean[][] myBitMap;
    private int myWidth;
    private int myHeight;

    public void createBitMap (int width, int height) {
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

    public boolean valueOf (int row, int column) {
        return myBitMap[row][column];
    }

    public void set (int row, int column, boolean value) {
        myBitMap[row][column] = value;
    }

}

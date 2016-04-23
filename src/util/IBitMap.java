package util;

import java.util.Iterator;

/**
 * Adding an interface because wanted to have different types of out of bounds access
 * behavior
 * @author jonathanim
 *
 */
public interface IBitMap {
    /**
     * Number of rows in the map
     * @return
     */
    int getHeight ();
    
    /**
     * Number of columns in the map
     * @return
     */
    int getWidth ();
    
    /**
     * Will flip the bit in the map at:
     * @param row
     * @param column
     */
    void flip (int row, int column);
    
    /** 
     * @param row
     * @param column
     * @return True if bit a row, column is 1, false if 0
     */
    boolean valueOf (int row, int column);
    
    /**
     * @param pos
     * @return True if bit at {@link util.ArrayPosition#getX()}, {@link util.ArrayPosition#getY()}
     *         is 1, false if 0
     */
    boolean valueOf (ArrayPosition pos);
    
    /**
     * This method may result in overflow type errors given double -> int conversion
     * @param coord
     * @return True if bit at {@code (int) } {@link util.Coordinate#getX()},
     *          {@code (int) } {@link util.Coordinate#getY()} is 1, false if 0
     */
    boolean valueOf (Coordinate coord);
    
    /**
     * Sets bit to be true = 1, false = 0 at row, column
     * @param row
     * @param column
     * @param value
     */
    void set (int row, int column, boolean value);
    
    /**
     * @return An iterator across the columns, then rows of the bit values<br>
     * 1 returns true<br>
     * 0 returns false
     */
    Iterator<Boolean> iterator ();
    
    /**
     * @return An iterator of the actual array access value pairings across the 
     * columns then the rows
     */
    Iterator<ArrayPosition> positionIterator ();
    
    /**
     * Access to the underlying array itself
     * @return
     */
    boolean[][] getBitMap ();

}

package util;

import javafx.scene.shape.Rectangle;


/**
 * Wrapper class for Rectangles which holds its rectangle object and row/column position.
 * 
 * @author Jin An
 *
 */
public class Tile {
    private Rectangle myTile;
    private int myRowPosition;
    private int myColPosition;

    public Tile (Rectangle tile, int row, int col) {
        myTile = tile;
        myRowPosition = row;
        myColPosition = col;
    }

    public int getRowPosition () {
        return myRowPosition;
    }

    public int getColPosition () {
        return myColPosition;
    }

    public Rectangle getTile () {
        return myTile;
    }
}

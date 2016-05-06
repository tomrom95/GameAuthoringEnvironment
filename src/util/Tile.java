package util;

import javafx.scene.paint.Color;
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

    public Tile (double rectSize, int row, int col) {
        myTile = new Rectangle(rectSize, rectSize);
        myTile.getStyleClass().add("r");
        myRowPosition = row;
        myColPosition = col;
    }

    public int getRowPosition () {
        return myRowPosition;
    }

    public int getColPosition () {
        return myColPosition;
    }

    public Rectangle getRect () {
        return myTile;
    }

    public boolean isTransparent() {
        return myTile.getFill() == Color.TRANSPARENT;
    }
    
    public boolean isRed() {
        return myTile.getFill() == Color.RED;
    }
    
    public void changeColor() {
        if (isRed())
            setTransparent();
        else
            setRed();
    }
    
    public void setRed () {
        myTile.setFill(Color.RED);
    }
    
    public void setTransparent () {
        myTile.setFill(Color.TRANSPARENT);
    }
}

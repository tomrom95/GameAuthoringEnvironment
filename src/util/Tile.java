// This entire file is part of my masterpiece.
// Jin An
/**
 * Rectangle class serves as a container class that has Grid shape and its positions within the
 * entire grid.
 * 
 * Most changes I made are related to extracting methods into Shape class, which Tile is related by
 * composition and adding a line that creates shape from ShapeFactory class. This class originally
 * contained a Rectangle shape but I made abstraction of shape so that the design supports flexible
 * extensions and follows the Open-Closed Principle. Influenced by many class readings and project
 * experience (especially Cell Society), I learned that abstraction is the key to the
 * Object-Oriented Programming. By recognizing opportunities to make things more abstract (shape),
 * and implementing it, I believe I demonstrated the solid understanding in Object-Oriented
 * Programming.
 * 
 * I took advantage of Factory Design Pattern to create different shapes. For instance, if string
 * "Rectangle" is passed to Tile, it will pass the string to ShapeFactory's getShape method and
 * create the shape we want. More detailed discussion will be on the ShapeFactory class.
 */
package util;

import javafx.scene.shape.Polygon;


/**
 * Wrapper class for Grid shapes which holds its shape object and row/column position.
 * Related to Shape object by composition, which is created by ShapeFactory.
 *
 * @author Jin An
 *
 */
public class Tile {
    private Shape myTile;
    private int myRowPosition;
    private int myColPosition;

    public Tile (double blockSize, int row, int column, String shape) {
        myRowPosition = row;
        myColPosition = column;
        myTile = (new ShapeFactory()).getShape(shape);
        myTile.makeShape(blockSize);
        getTileObject().getStyleClass().add("r");
    }

    public int getRowPosition () {
        return myRowPosition;
    }

    public int getColPosition () {
        return myColPosition;
    }

    public Shape getShapeObject () {
        return myTile;
    }

    public Polygon getTileObject () {
        return myTile.getShape();
    }
}

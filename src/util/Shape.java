// This entire file is part of my masterpiece.
// Jin An
/**
 * Shape is the highest inheritance hierarchy class of Shape, which is extended by Rectangle class.
 * The great thing about this class is that it captures the common functionality shared between all
 * subclasses. This is why I used inheritance instead of interface even though factory design pattern is
 * usually used with interface.
 */

package util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


/**
 * Highest inheritance hierarchy class of Shape.
 * 
 * @author Jin An
 *
 */
public abstract class Shape {

    protected static final double ZERO = 0;
    protected Polygon myShape = new Polygon();

    public abstract void makeShape (double blockSize);

    public Polygon getShape () {
        return myShape;
    }

    public boolean isTransparent () {
        return myShape.getFill() == Color.TRANSPARENT;
    }

    public boolean isRed () {
        return myShape.getFill() == Color.RED;
    }

    public void setRed () {
        myShape.setFill(Color.RED);
    }

    public void setTransparent () {
        myShape.setFill(Color.TRANSPARENT);
    }

    public void changeColor () {
        if (isRed())
            setTransparent();
        else
            setRed();
    }
}

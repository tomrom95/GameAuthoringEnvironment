// This entire file is part of my masterpiece.
// Jin An
/**
 * This is an example subclass of Shape parent class. The reason why I chose inheritance over
 * interface is that there are some common methods that different Shape subclasses share such as
 * getShape() and changeColor(). Thus, I used Shape to capture the common functionality of
 * containing Polygon object and returning relevant color information (convenient methods that are
 * called multiple times in GridRenderer Class).
 * 
 * If I were to create other shapes that are not Polygon such as circle, I can easily extend it by
 * modifying the inheritance hierarchy. For instance, I can have middle-subclass between Rectangle
 * and Shape such as Polygon or RoundShape. Then, Rectangle can extend Polygon Class that extends
 * Shape.
 */

package util;

/**
 * Subclass of Shape parent class
 * @author Jin An
 *
 */
public class Rectangle extends Shape {

    @Override
    public void makeShape (double blockSize) {
        myShape.getPoints().addAll(new Double[] { ZERO, ZERO, blockSize, ZERO, blockSize,
                                                 blockSize, ZERO, blockSize });
    }
}

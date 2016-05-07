// This entire file is part of my masterpiece.
// Jin An
/**
 * As its class name directly suggest, ShapeFactory serves to instantiate and return different
 * shapes based on the string passed from Tile class. Even though this is a short class, I believe
 * it utilizes many concepts that we learned throughout the semester. These are the functionalities
 * of this class:
 * 
 * - Uses Factory Design Pattern to create shape in a clean, concise way.
 * - Uses ResourceBundle to wrap around unprotected (hard-coded) String.
 * - Uses Enumeration to read all the keys and compare with the string passed from Tile class.
 * - Uses reflection to instantiate the class based on its name
 * - Deals with exception appropriately. In this case, if there is any exception caught or if there is
 * no string that match, the method will return default shape which is Rectangle. 
 */

package util;

import java.util.Enumeration;
import java.util.ResourceBundle;


/**
 * Factory class for creating Shape.
 * 
 * @author Jin An
 *
 */
public class ShapeFactory {

    private ResourceBundle myShapes = ResourceBundle.getBundle("defaults/tile_shape");

    public Shape getShape (String shapeType) {
        Enumeration<String> keys = myShapes.getKeys();
        while (keys.hasMoreElements()) {
            if (shapeType.equalsIgnoreCase(keys.nextElement())) {
                Class<?> clas = null;
                try {
                    clas = Class.forName(shapeType);
                }
                catch (ClassNotFoundException e1) {
                    return returnDefaultShape();
                }
                try {
                    return (Shape) clas.newInstance();
                }
                catch (InstantiationException | IllegalAccessException e) {
                    return returnDefaultShape();
                }
            }
        }
        return returnDefaultShape();
    }

    private Shape returnDefaultShape () {
        return new Rectangle();
    }
}

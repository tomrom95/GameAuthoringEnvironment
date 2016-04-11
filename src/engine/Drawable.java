package engine;

import engine.modules.IGraphicModule;
import javafx.beans.property.ObjectProperty;
import util.Coordinate;


/**
 * This interface imposes the ability for a class to have a drawing component that is capable of
 * generating a visual representation.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface Drawable {

    /**
     * @return the component for this class that is capable of generating a visual representation
     *         for it.
     */
    IGraphicModule getDrawer ();

    /**
     * @return the location of the drawable
     */
    Coordinate getLocation ();
}

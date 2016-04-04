package modules;

import engine.IModule;
import javafx.beans.property.ObjectProperty;
import util.Coordinate;


/**
 * This interface represents a module of a sprite that handles its movement and positioning
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IMovementModule extends IModule {

    ObjectProperty<Coordinate> getLocationProperty ();
}

package engine;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import util.Coordinate;


/**
 * This interface represents the
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 * @author Jeremy Schreck
 *
 */
public interface ISprite extends Drawable, Updateable, Affectable {

    ObjectProperty<IProfile> getProfileProperty ();

    ObjectProperty<Coordinate> getLocation ();

    ObjectProperty<IMovementModule> getMovementStrategyProperty ();

    ObservableList<ObjectProperty<IModule>> getModulesProperty ();

    ObservableList<ObjectProperty<IAttribute>> getAttributesProperty ();

    ObservableList<ObjectProperty<IResource>> getResourcesProperty ();

}

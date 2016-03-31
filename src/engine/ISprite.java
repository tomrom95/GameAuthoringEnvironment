package engine;

import gameauthoring.IProfile;
import javafx.beans.property.SimpleObjectProperty;
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

    SimpleObjectProperty<IProfile> getProfileProperty ();
    
    SimpleObjectProperty<SpriteType> getType ();

    SimpleObjectProperty<Coordinate> getLocation ();

    SimpleObjectProperty<IMovementModule> getMovementStrategyProperty ();

    ObservableList<IModule> getModulesProperty ();

    ObservableList<IAttribute> getAttributesProperty ();

    ObservableList<IResource> getResourcesProperty ();

}

package engine;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import modules.IModule;
import modules.IMovementModule;
import util.Bound;
import util.Coordinate;


/**
 * This interface represents a sprite in a game, that can be updated and can respond to external
 * events and stimuli. 
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

    ObjectProperty<Coordinate> getLocation ();

    ObjectProperty<IMovementModule> getMovementStrategyProperty ();

    ObservableList<ObjectProperty<? extends IModule>> getModulesProperty ();

    ObservableList<ObjectProperty<IAttribute>> getAttributes ();

    ObservableList<ObjectProperty<IResource>> getResourcesProperty ();

    Bound getBounds ();

    ObjectProperty<SpriteType> getType ();

}

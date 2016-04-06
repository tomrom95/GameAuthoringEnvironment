package engine;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import modules.IModule;
import modules.IMovementModule;
import util.Bound;


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
public interface ISprite extends Drawable, Updateable, Affectable, IPositionable {

    ObjectProperty<IMovementModule> getMovementStrategyProperty ();

    ObservableList<ObjectProperty<? extends IModule>> getModulesProperty ();

    ObservableList<ObjectProperty<IAttribute>> getAttributes ();

    ObservableList<ObjectProperty<IResource>> getResourcesProperty ();
    
    Bound getBounds ();

}

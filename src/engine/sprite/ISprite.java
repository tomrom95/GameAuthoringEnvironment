package engine.sprite;

import engine.Affectable;
import engine.AttributeManager;
import engine.Drawable;
import engine.IAttribute;
import engine.IPositionable;
import engine.IResource;
import engine.Updateable;
import engine.modules.IModule;
import engine.modules.IMovementModule;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import util.Bounds;


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
public interface ISprite extends Drawable, Updateable, Affectable, IPositionable {

    ObjectProperty<IMovementModule> getMovementStrategyProperty ();

    ObservableList<ObjectProperty<? extends IModule>> getModulesProperty ();

    ObservableList<ObjectProperty<IAttribute>> getAttributes ();

    ObservableList<ObjectProperty<IResource>> getResourcesProperty ();
    
    ObjectProperty<AttributeManager> getAttributeManager();

    Bounds getBounds ();

    ObjectProperty<SpriteType> getType ();

}

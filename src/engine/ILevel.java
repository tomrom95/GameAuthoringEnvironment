package engine;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


/**
 * This interface represents a level of a game and all of its associated editable behavior.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface ILevel extends Updateable, IAdder {

    /**
     * @return the condition manager for this level
     */
    ObservableList<ObjectProperty<ICondition>> getConditionsPropertyList ();

    /**
     * Add a global resource to this level
     *
     * @param resource to be added
     */
    void addGlobalResource (IResource resource);

    /**
     * @return an observable list of the sprites in this level
     */
    ObservableList<ObjectProperty<ISprite>> getSprites ();

    /**
     * This method call will control transition between levels, to stay on the current
     * level
     * 
     * @return the next level after this one
     */
    ILevel getNextLevel ();

    /**
     * @return whether or not level should be switched out for the next one
     */
    boolean shouldSwitchLevel ();
}

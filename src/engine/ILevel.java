package engine;

import javafx.beans.property.SimpleObjectProperty;
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
     * Add a condition to this level
     *
     * @param condition to be added
     */
    void addCondition (ICondition condition);

    /**
     * @return the condition manager for this level
     */
    IConditionManager getConditionManager ();

    /**
     * Add a global resource to this level
     *
     * @param resource to be added
     */
    void addGlobalResource (IResource resource);

    /**
     * @return an observable list of the sprites in this level
     */
    ObservableList<SimpleObjectProperty<ISprite>> getSprites ();
}

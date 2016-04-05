package engine;

import java.util.List;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


/**
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface ILevelManager extends IAdder {

    /**
     * @return the current level
     */
    ObjectProperty<ILevel> getCurrentLevel ();

    /**
     * @return an ObservableList of all the levels associated with this manager
     */
    ObservableList<ObjectProperty<ILevel>> getLevels ();

    /**
     * returns the Drawables of the current level
     * @return
     */
    ObservableList<? extends ObjectProperty<? extends Drawable>> getDrawables ();

    void internalizeKeyEvents (List<KeyIOEvent> list);

    void internalizeMouseEvents (List<MouseIOEvent> list);
    
}

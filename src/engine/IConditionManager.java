package engine;

import java.util.List;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


/**
 * This interface represents a component that manages IConditions and allows a Game to update
 * these conditions
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IConditionManager extends Updateable {

    /**
     * @return the observable list of condition that this class manages
     */
    ObservableList<ObjectProperty<ICondition>> getConditionListProperty ();

    void internalizeMouseEvents (List<MouseIOEvent> list);

    void internalizeKeyEvents (List<KeyIOEvent> list);
}

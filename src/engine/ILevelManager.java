package engine;

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
    ILevel getCurrentLevel ();

    /**
     * @return an ObservableList of all the levels associated with this manager
     */
    ObservableList<ObjectProperty<ILevel>> getLevels ();
    
}

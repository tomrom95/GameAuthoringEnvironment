package engine;

import javafx.beans.property.BooleanProperty;

/**
 * Interface for a generic check 
 * @author RyanStPierre
 *
 */
public interface ICheck extends Updateable {

    /**
     * Subtracts cost
     */
    void alterAttribute ();
    
    /**
     * Constantly tracks the status of the check 
     * @return dynamic, not static like check
     */
    BooleanProperty getStatus ();
}

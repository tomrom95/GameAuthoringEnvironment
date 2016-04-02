package engine;


import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


/**
 * This interface manages a list of sprites, and handles access to them.
 * Effects have been moved to a conditions manager, which will have
 * top down views of the individual sprites themselves, 
 * thus this interface is not repsonsible for passing events to the sprites
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface ISpriteManager extends Updateable, IAdder {

    /**
     * @return the observable list of sprites
     */
    ObservableList<ObjectProperty<ISprite>> getSprites ();
    
    
}

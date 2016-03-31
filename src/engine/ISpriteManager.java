package engine;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;


/**
 * This interface manages a list of sprites, and handles access to them.
 * 
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface ISpriteManager extends Updateable, Affectable {

    /**
     * @return the observable list of sprites
     */
    ObservableList<SimpleObjectProperty<ISprite>> getSprites ();
}

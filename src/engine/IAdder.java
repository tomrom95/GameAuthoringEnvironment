package engine;

import engine.sprite.ISprite;
import javafx.beans.property.ObjectProperty;
import util.Coordinate;

/**
 * Stores queue of Sprites to be added on next game cycle
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IAdder extends Updateable {

    /**
     * @param sprite to be added in the next game cycle
     * @param coordinate where the Sprite is added
     */

    void add (ISprite sprite, Coordinate coordinate);
    
    void add (ISprite sprite);
    
    
}

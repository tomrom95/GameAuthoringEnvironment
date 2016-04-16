package engine;

import engine.sprite.ISprite;
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
     * Add a sprite in the next update cycle
     * 
     * @param sprite to be added
     * @param coordinate where the Sprite is added
     */
    void bufferedAdd (ISprite sprite, Coordinate coordinate);

    /**
     * Add a sprite in the next update cycle
     * 
     * @param sprite to be added
     */
    void bufferedAdd (ISprite sprite);

}

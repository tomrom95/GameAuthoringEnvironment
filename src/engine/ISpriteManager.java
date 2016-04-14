package engine;

import java.util.List;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import util.Coordinate;


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
    List<ISprite> getSprites ();

    List<? extends Drawable> getDrawables ();

    /**
     * @param list of key events to be processed
     */
    void internalizeKeyEvents (List<KeyIOEvent> list);

    /**
     * @param list of mouse events to be processed
     */
    void internalizeMouseEvents (List<MouseIOEvent> list);

    /**
     * @param sprite to be removed
     */
    void remove (ISprite sprite);

    void add (ISprite sprite, Coordinate coordinate);

}

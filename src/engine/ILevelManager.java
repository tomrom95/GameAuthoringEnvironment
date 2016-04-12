package engine;

import java.util.List;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import graphics.ImageGraphic;


/**
 * This interface creates the behavior for the game to handle and manage the levels that are created
 * by the user.
 * This interface provides the method calls for the stored levels in a game.
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
    List<ILevel> getLevels ();

    /**
     * returns the Drawables of the current level
     *
     * @return
     */
    List<? extends Drawable> getDrawables ();

    /**
     * @param list of key events to internalize
     */
    void internalizeKeyEvents (List<KeyIOEvent> list);

    /**
     * @param list of mouse events to internalize
     */
    void internalizeMouseEvents (List<MouseIOEvent> list);

    /**
     * @param sprite to be removed from the current level
     */
    void remove (ISprite sprite);

    /**
     * @return the background image of the current level
     */
    ImageGraphic getBackgroundImage ();

    /**
     *
     * @param newLevel the level to add to the game
     */
    void createNewLevel (ILevel newLevel);
}

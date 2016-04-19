package engine;

import java.util.List;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import graphics.ImageGraphic;
import util.Coordinate;


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
public interface ILevelManager extends IAdder, IEventInternalizer {

    /**
     * @return the current level
     */
    ILevel getCurrentLevel ();

    /**
     * @return an ObservableList of all the levels associated with this manager
     */
    List<ILevel> getLevels ();

    /**
     * @return the list of Drawables of the current level
     */
    List<? extends Drawable> getDrawables ();

    /**
     * @param sprite to be removed from the current level
     */
    void remove (ISprite sprite);

    /**
     * @return the background image of the current level
     */
    ImageGraphic getBackgroundImage ();

    /**
     * @param newLevel the level to add to the game
     */
    void createNewLevel (ILevel newLevel);

    void add (ISprite sprite);

    void add (ISprite sprite, Coordinate coordinate);
}

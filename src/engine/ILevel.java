package engine;

import java.util.List;
import engine.conditions.ICondition;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import graphics.ImageGraphic;
import javafx.collections.ObservableList;
import util.Coordinate;


/**
 * This interface represents a level of a game and all of its associated editable behavior.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface ILevel extends Updateable, IAdder {

    /**
     * @return the condition manager for this level
     */
    ObservableList<ICondition> getConditionsListProperty ();

    /**
     * @return the global attribute manager for this level
     */
    IAttributeManager getAttributeManager ();

    /**
     * @return the Image of the background of the level
     */
    ImageGraphic getBackgroundImage ();

    void setBackgroundImage (ImageGraphic graphic);

    /**
     * Add a global resource to this level
     *
     * @param resource to be added
     */
    void addGlobalResource (IResource resource);

    /**
     * @return an observable list of the sprites in this level
     */
    List<ISprite> getSprites ();

    /**
     * This method call will control transition between levels, to stay on the current
     * level
     *
     * @return the next level after this one
     */
    ILevel getNextLevel ();

    /**
     * @return whether or not level should be switched out for the next one
     */
    boolean shouldSwitchLevel ();

    List<? extends Drawable> getDrawables ();

    /**
     * @param list of key events to be processed
     */
    void internalizeKeyEvents (List<KeyIOEvent> list);

    /**
     * @param list of key events to be processed
     */
    void internalizeMouseEvents (List<MouseIOEvent> list);

    /**
     * @param sprite to be removed
     */

    void remove (ISprite sprite);

    void add (ISprite sprite, Coordinate coordinate);

    void add (ISprite sprite);

}

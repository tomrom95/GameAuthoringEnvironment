package engine;

import java.util.List;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import graphics.ImageGraphic;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


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
    ObservableList<ObjectProperty<ICondition>> getConditionsPropertyList ();
    
    /**
     * @return the Image of the background of the level
     */
    ObjectProperty<ImageGraphic> getBackgroundImageProperty ();

    /**
     * Add a global resource to this level
     *
     * @param resource to be added
     */
    void addGlobalResource (IResource resource);

    /**
     * @return an observable list of the sprites in this level
     */
    ObservableList<ObjectProperty<ISprite>> getSprites ();

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

    ObservableList<? extends ObjectProperty<? extends Drawable>> getDrawables ();
    
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
    
    void remove(ObjectProperty<ISprite> sprite);
   
}

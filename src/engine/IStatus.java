package engine;

/**
 * This interface represents a module that handles the status of a sprite
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IStatus extends Affectable, GameEventHandler {

    /**
     * @return whether this sprite should be removed from the game
     */
    boolean shouldBeRemoved ();
    
    /**
     * Should set to be removed
     */
    void remove ();
}

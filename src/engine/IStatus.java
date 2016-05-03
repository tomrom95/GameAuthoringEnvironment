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

    /**
     * Returns whether or not the game engine should consider this sprite as
     * obstructing for the purposes of generating the obstruction bitmaps
     *
     * @return
     */
    boolean doesObstruct ();

    /**
     * This
     *
     * @param value {@code true} if this sprite obstructs, {@code false} if it does not
     */
    void setObstruction (boolean value);

    /**
     * Whether or not the sprite should be considered a goal
     *
     * @return
     */
    boolean isGoal ();

    /**
     * Set whether or not the sprite should be considered a goal
     *
     * @param value
     */
    void setIsGoal (boolean value);
}

package engine;

/**
 * This interface provides the method calls to for an ILevelManager to determine whether a level is
 * completed and should proceed
 * to the next level.
 *
 * TODO finish implementing this interface
 */
public interface INextLevelManager extends Updateable, IEventInternalizer {

    /**
     * This will return the value as calculated during the update call to help control
     * program flow in the class which holds this
     *
     * @return
     */
    boolean shouldGoToNextLevel ();

    /**
     * Will return the level that the user should transition to
     *
     * @return
     */
    ILevel getNextLevel ();

    void setWinLevel (ILevel winLevel);

    void setLoseLevel (ILevel loseLevel);

}

package engine;

/**
 * This interface provides the method to determine whether the logic is valid for a level to advance
 * to its next
 * sequential level in the user created IGame.
 * TODO finish implementing this interface
 */
public interface INextLevelCondition extends Updateable {
    /**
     * @return whether or not the condition thinks the game should move to the next level
     */
    boolean shouldProceed ();

    /**
     * The conditions themselves will be responsible for holding which level object you would like
     * to transition to
     *
     * @return
     */
    ILevel getNextLevel ();
}

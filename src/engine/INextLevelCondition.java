package engine;

public interface INextLevelCondition extends Updateable {
    /**
     * Will return whether or not the condition thinks the game should move to the next level
     * 
     * @return
     */
    boolean shouldProceed ();

    /**
     * The conditions themselves will be responsible for holding which level object you would like
     * to transition to
     * @return
     */
    ILevel getNextLevel ();
}

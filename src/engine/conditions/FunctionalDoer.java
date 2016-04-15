package engine.conditions;

/**
 * This interface represents an executable item that should be executed conditionally
 * TODO can this class not just use one of the functional interfaces java already defines? like
 * function?
 * 
 * @author Jonathan Im
 *
 */
public interface FunctionalDoer {
    /**
     * Execute the specified implementation
     */
    void doIt ();
}

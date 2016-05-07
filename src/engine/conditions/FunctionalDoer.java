package engine.conditions;

/**
 * This interface represents an executable item that should be executed conditionally
 * TODO can this class not just use one of the functional interfaces java already defines? like
 * Runnable?
 * http://stackoverflow.com/questions/4480334/how-to-call-a-method-stored-in-a-hashmap-java
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

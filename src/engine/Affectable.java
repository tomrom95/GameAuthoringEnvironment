package engine;

/**
 * This interface imposes the ability to respond to an incoming effect and handle it as necessary.
 * The implementing class may or may not change a result of an applied effect.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface Affectable {

    /**
     * Apply a given effect to this object
     *
     * @param effect the effect to apply
     */
    void applyEffect (IEffect effect);
    
    /**
     * Respond appropriately to a global interaction event
     * @param event
     */
    void registerEvent (IInteractionEvent event);
}

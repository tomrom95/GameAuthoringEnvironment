package engine;

/**
 * This class represents an effect to be applied to an attribute.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IEffect {

    /**
     * Applies the changes from this effect to a a given attribute
     *
     * @param attribute to be affected
     */
    void applyToAttribute (IAttribute attribute);
}

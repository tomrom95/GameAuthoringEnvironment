package engine.effects;

import engine.AttributeType;
import engine.IAttribute;
import engine.Updateable;


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
public interface IEffect extends Updateable {

    /**
     * @return AttributeType specifying the type of Attribute to which the Effect is intended to.
     *         influence
     */
    AttributeType getAttributeType ();

    /**
     * Applies the changes from this effect to a a given attribute.
     *
     * @param attribute to be affected
     */
    void applyToAttribute (IAttribute attribute);

    /**
     * @return true if the effect has completed, false otherwise
     */
    boolean hasCompleted ();

    /**
     * To support proper tracking of internal state when applied across attributes
     * separate instances of the prototypical effect must be created
     * 
     * @return
     */
    IEffect makeCopy ();
}

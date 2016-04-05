package effects;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import util.TimeDuration;


/**
 * Class intended to grab the common functionality of IEffect sub-types.
 * 
 * @author RyanStPierre
 *
 */

public abstract class Effect implements IEffect {

    private AttributeType myAttributeType;
    private IAttribute myEffectLength;
    private IAttribute myAlteringValue;

    /**
     * Constructor for an incoming constant.
     * 
     * @param type AttributeType to which the Effect applied
     * @param alteringValue the amount of the decrement
     */
    public Effect (AttributeType type,  IAttribute effectLength, double alteringValue) {
        myAttributeType = type;
        myEffectLength = effectLength;
        myAlteringValue = new Attribute(alteringValue, AttributeType.CONSTANT);
    }
    
    /**
     * Constructor where altering value amount is tied to another attribute .
     * 
     * @param type type AttributeType to which the Effect applied
     * @param alteringValue the amount of the decrement- tied to another dynamic
     *        attribute
     */
    public Effect (AttributeType type, IAttribute effectLength, IAttribute alteringValue) {
        myAttributeType = type;
        myEffectLength = effectLength;
        myAlteringValue = alteringValue;
    }

    protected double getAlteringValue () {
        return myAlteringValue.getValueProperty().get();
    }

    protected boolean matchesMyType (AttributeType other) {
        return getAttributeType().equals(other);
    }

    public AttributeType getAttributeType () {
        return myAttributeType;
    }
    
   
    public boolean hasCompleted (TimeDuration duration) {
        myEffectLength.getValueProperty().set(duration.getMillis());
        return myEffectLength.getValueProperty().get() < 0;
    }
}

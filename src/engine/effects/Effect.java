package engine.effects;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.definitions.concrete.AttributeDefinition;
import util.TimeDuration;


/**
 * Class intended to grab the common functionality of IEffect sub-types.
 *
 * @author RyanStPierre
 *
 */

public abstract class Effect implements IEffect {

    private AttributeType myAttributeType;
    private IAttribute myEffectLengthAttribute;
    private IAttribute myAlteringAttribute;
    private AttributeDefinition myAlteringAttributeDefinition;

    /**
     * Constructor for an incoming constant.
     *
     * @param type AttributeType to which the Effect applied
     * @param alteringValue the amount of the decrement
     */
    public Effect (AttributeDefinition type, IAttribute effectLength, double alteringValue) {
        myAlteringAttributeDefinition = type;
        myAttributeType = type.getAttributeType();
        myEffectLengthAttribute = effectLength;
        myAlteringAttribute = new Attribute(alteringValue, AttributeType.CONSTANT);
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
        myEffectLengthAttribute = effectLength;
        myAlteringAttribute = alteringValue;
    }

    public IAttribute getEffectLengthAttribute () {
        return myEffectLengthAttribute;
    }

    public IAttribute getAlteringAttribute () {
        return myAlteringAttribute;
    }

    protected double getAlteringValue () {
        return myAlteringAttribute.getValueProperty().get();
    }

    protected void setAlteringValue (double value) {
        myAlteringAttribute.getValueProperty().set(value);
    }

    protected boolean matchesMyType (AttributeType other) {
        return getAttributeType().equals(other);
    }

    @Override
    public AttributeType getAttributeType () {
        return myAttributeType;
    }
    
    public AttributeDefinition getAttributeDefinition () {
        return myAlteringAttributeDefinition;
    }

    @Override
    public boolean hasCompleted () {
        return myEffectLengthAttribute.getValueProperty().get() < 0;
    }

    @Override
    public void update (TimeDuration duration) {
        myEffectLengthAttribute.getValueProperty()
                .set(myEffectLengthAttribute.getValueProperty().get() - duration.getMillis());
    }

}

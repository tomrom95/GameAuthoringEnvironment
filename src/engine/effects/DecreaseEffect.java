package engine.effects;

import engine.AttributeType;
import engine.IAttribute;
import engine.definitions.concrete.AttributeDefinition;


/**
 * Effect intended to decease an incoming attribute by a value.
 *
 * @author RyanStPierre
 *
 */

public class DecreaseEffect extends Effect {

    // TODO should this also have constructors that allow specification of just primitive double
    // effectLength?
    public DecreaseEffect (AttributeDefinition type,
                           IAttribute effectLength,
                           double decreaseAmount) {
        super(type, effectLength, decreaseAmount);
    }

    public DecreaseEffect (AttributeType type, IAttribute effectLength, IAttribute decreaseAmount) {
        super(type, effectLength, decreaseAmount);
    }

    @Override
    public void applyToAttribute (IAttribute incoming) {
        if (matchesMyType(incoming.getType())) {
            double newValue = incoming.getValueProperty().get() - getAlteringValue();
            incoming.setValue(newValue);
        }

    }

    @Override
    public IEffect makeCopy () {
        return new DecreaseEffect(getAttributeDefinition(),
                                  getEffectLengthAttribute().makeCopy(),
                                  getAlteringValue());
    }

}

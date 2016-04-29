package engine.effects;

import engine.AttributeType;
import engine.IAttribute;
import engine.definitions.concrete.AttributeDefinition;


/**
 * Effect intended to increase an incoming attribute by a value.
 *
 * @author RyanStPierre
 *
 */
public class IncreaseEffect extends Effect {

    public IncreaseEffect (AttributeDefinition type, IAttribute effectLength, double increaseAmount) {
        super(type, effectLength, increaseAmount);
    }

    public IncreaseEffect (AttributeType type, IAttribute effectLength, IAttribute increaseAmount) {
        super(type, effectLength, increaseAmount);
    }

    @Override
    public void applyToAttribute (IAttribute incoming) {
        if (matchesMyType(incoming.getType())) {
            double newValue = incoming.getValueProperty().get() + getAlteringValue();
            incoming.setValue(newValue);
        }
    }

    @Override
    public IEffect makeCopy () {
        return new IncreaseEffect(getAttributeDefinition(),
                                  getEffectLengthAttribute().makeCopy(),
                                  getAlteringValue());
    }
}

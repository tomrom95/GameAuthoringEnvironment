package effects;

import engine.AttributeType;
import engine.IAttribute;


/**
 * Effect intended to increase an incoming attribute by a value.
 * 
 * @author RyanStPierre
 *
 */

public class DecreaseEffect extends Effect {
    
    public DecreaseEffect (AttributeType type, double decreaseAmount) {
        super(type, decreaseAmount);
    }
 
    public DecreaseEffect (AttributeType type, IAttribute decreaseAmount) {
        super(type, decreaseAmount);
    }

    @Override
    public void applyToAttribute (IAttribute incoming) {
        if (matchesMyType(incoming.getType())) {
            double newValue = incoming.getValueProperty().get() - getAlteringValue();
            incoming.setValue(newValue);
        }

    }

}

package effects;

import engine.AttributeType;
import engine.IAttribute;

/**
 * Effect intended to multiply an incoming attribute by a proportion
 * @author RyanStPierre
 *
 */
public class ProportionEffect extends Effect {

    public ProportionEffect (AttributeType type, IAttribute effectLength, double proportion) {
        super(type,  effectLength, proportion);
    }
 
    public ProportionEffect (AttributeType type, IAttribute effectLength, IAttribute proportion) {
        super(type, effectLength, proportion);
    }

    @Override
    public void applyToAttribute (IAttribute incoming) {
        if (matchesMyType(incoming.getType())) {
            double newValue = incoming.getValueProperty().get() * getAlteringValue();
            incoming.setValue(newValue);
        }
    }
}

package effects;

import engine.AttributeType;
import engine.IAttribute;

/**
 * Effect intended to multiply an incoming attribute by a proportion
 * @author RyanStPierre
 *
 */
public class ProportionEffect extends Effect {

    public ProportionEffect (AttributeType type, double proportion) {
        super(type, proportion);
    }
 
    public ProportionEffect (AttributeType type, IAttribute proportion) {
        super(type, proportion);
    }

    @Override
    public void applyToAttribute (IAttribute incoming) {
        if (matchesMyType(incoming.getType())) {
            double newValue = incoming.getValueProperty().get() * getAlteringValue();
            incoming.setValue(newValue);
        }
    }
}

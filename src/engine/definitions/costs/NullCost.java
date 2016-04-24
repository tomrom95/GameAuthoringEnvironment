package engine.definitions.costs;

import engine.AttributeType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class NullCost implements ICost {
    
    private static final String EMPTY_ATTRIBUTE = "";

    @Override
    public double getCostAmount () {
        return 0;
    }

    @Override
    public AttributeType getAttributeType () {
        return new AttributeType(EMPTY_ATTRIBUTE);
    }

    @Override
    public void buySprite () {
        // do nothing
    }

    @Override
    public BooleanProperty canPlace () {
        return new SimpleBooleanProperty(true);
    }

}

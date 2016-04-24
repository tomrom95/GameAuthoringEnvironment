package engine.definitions.costs;

import engine.AttributeType;
import javafx.beans.property.BooleanProperty;

public interface ICost {
    
    double getCostAmount ();
    
    AttributeType getAttributeType ();
    
    void buySprite ();
    
    BooleanProperty canPlace ();

}

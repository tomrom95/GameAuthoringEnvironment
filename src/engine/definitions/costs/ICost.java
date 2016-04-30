package engine.definitions.costs;

import engine.AttributeType;
import engine.definitions.concrete.AttributeDefinition;
import javafx.beans.property.BooleanProperty;

public interface ICost {
    
    double getCostAmount ();
    
    AttributeType getAttributeType ();
    
    AttributeDefinition getAttributeDefinition ();
    
    void buySprite ();
    
    BooleanProperty canPlace ();
    
    boolean hasCost();

}

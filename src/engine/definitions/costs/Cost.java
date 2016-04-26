package engine.definitions.costs;

import engine.AttributeType;
import engine.Check;
import engine.IGame;
import engine.definitions.concrete.AttributeDefinition;
import javafx.beans.property.BooleanProperty;

public class Cost implements ICost {
    
    private AttributeDefinition myAttributeDefinition;
    private double myAmount;
    private Check myCheck;
    
    public Cost (IGame myGame,
                 AttributeDefinition attribute,
                 double cost) {
        myAttributeDefinition = attribute;
        myAmount = cost;
        myCheck = new Check(myGame.getAttributeManager(), attribute.getAttributeType(), cost);
    }

    @Override
    public double getCostAmount () {
        return myAmount;
    }

    @Override
    public AttributeType getAttributeType () {
        return myAttributeDefinition.getAttributeType();
    }

    @Override
    public AttributeDefinition getAttributeDefinition () {
        return myAttributeDefinition;
    }

    @Override
    public void buySprite () {
        myCheck.alterAttribute();
    }

    @Override
    public BooleanProperty canPlace () {
        return myCheck.getStatus();
    }


}

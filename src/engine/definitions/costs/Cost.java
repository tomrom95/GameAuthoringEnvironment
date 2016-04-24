package engine.definitions.costs;

import engine.AttributeType;
import engine.Check;
import engine.IGame;
import javafx.beans.property.BooleanProperty;

public class Cost implements ICost {
    
    private AttributeType myType;
    private double myAmount;
    private Check myCheck;
    
    public Cost (IGame myGame,
                 AttributeType type,
                 double cost) {
        myType = type;
        myAmount = cost;
        myCheck = new Check(myGame.getAttributeManager(), type, cost);
    }

    @Override
    public double getCostAmount () {
        return myAmount;
    }

    @Override
    public AttributeType getAttributeType () {
        return myType;
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

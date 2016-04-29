package engine.definitions.upgrades;

import engine.Check;
import engine.ICheck;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;


public class SpriteUpgradeDefinition extends UpgradeDefinition {


    public SpriteUpgradeDefinition (IGame myGame,
                                    SpriteDefinition nextUpgrade,
                                    AttributeDefinition type,
                                    double cost) {
        super(myGame, nextUpgrade, type, cost);
       
    }

    public SpriteUpgradeDefinition () {
        super();
    }

    @Override
    protected ICheck getCheck (Positionable parent) {
        return new Check(parent.getAttributeManager(), getType(), getCost());
        
    }
}

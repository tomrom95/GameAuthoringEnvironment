package engine.definitions.upgrades;

import engine.Check;
import engine.ICheck;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;


public class GlobalUpgradeDefinition extends UpgradeDefinition {

    public GlobalUpgradeDefinition () {
        super();
    }

    public GlobalUpgradeDefinition (IGame myGame,
                                    SpriteDefinition nextUpgrade,
                                    AttributeDefinition type,
                                    double cost) {
        super(myGame, nextUpgrade, type, cost);
    }

    @Override
    protected ICheck getCheck (Positionable parent) {
        return new Check(getGame().getAttributeManager(), getType(), getCost());

    }

}

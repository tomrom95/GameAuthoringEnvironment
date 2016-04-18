package engine.definitions.upgrades;

import engine.AttributeType;
import engine.Check;
import engine.ICheck;
import engine.IGame;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import engine.modules.IModule;
import engine.modules.UpgradeModule;


public class GlobalUpgradeDefinition extends UpgradeDefinition {

    public GlobalUpgradeDefinition (IGame game, SpriteDefinition upgrade, AttributeType type, double cost) {
        super(game, upgrade, type, cost);
       
    }

    @Override
    protected ICheck getCheck (Positionable parent) {
       return new Check (getGame().getAttributeManager(), getType(), getCost());
      
    }

}

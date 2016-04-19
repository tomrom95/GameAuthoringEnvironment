package engine.definitions.upgrades;

import engine.AttributeType;
import engine.Check;
import engine.IAdder;
import engine.ICheck;
import engine.IGame;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import engine.modules.IModule;
import engine.modules.UpgradeModule;


public class SpriteUpgradeDefinition extends UpgradeDefinition {


    @Override
    protected ICheck getCheck (Positionable parent) {
        return new Check(parent.getAttributeManager(), getType(), getCost());
        
    }
}

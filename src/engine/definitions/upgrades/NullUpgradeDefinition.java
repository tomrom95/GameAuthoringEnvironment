package engine.definitions.upgrades;

import engine.AttributeType;
import engine.ICheck;
import engine.IGame;
import engine.NullFalseCheck;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import engine.modules.NullUpgradeModule;
import engine.modules.UpgradeModule;


public class NullUpgradeDefinition extends UpgradeDefinition {
    
    public NullUpgradeDefinition () {
        //Do nothing
    }
    
    public NullUpgradeDefinition (IGame myGame,
                                  SpriteDefinition nextUpgrade,
                                  AttributeType type,
                                  double cost) {
        super(myGame, nextUpgrade, type, cost);
      
    }

    public UpgradeModule create (Positionable parent) {
        return new NullUpgradeModule(getGame(), getUpgrade(), getCheck(parent), parent);
    }
    
    @Override
    protected ICheck getCheck (Positionable parent) {
        return new NullFalseCheck();
    }

}

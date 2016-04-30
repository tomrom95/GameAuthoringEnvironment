package engine.definitions.upgrades;
import engine.ICheck;
import engine.IGame;
import engine.NullFalseCheck;
import engine.Positionable;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.modules.NullUpgradeModule;
import engine.modules.UpgradeModule;


public class NullUpgradeDefinition extends UpgradeDefinition {
    
    public NullUpgradeDefinition () {
        this(null, null, null, 0);
    }
    
    public NullUpgradeDefinition (IGame myGame,
                                  SpriteDefinition nextUpgrade,
                                  AttributeDefinition definition,
                                  double cost) {
        super(myGame, nextUpgrade, definition, cost);
        setIsUpgradable(false);
      
    }

    public UpgradeModule create (Positionable parent) {
        return new NullUpgradeModule(getGame(), getUpgrade(), getCheck(parent), parent);
    }
    
    @Override
    protected ICheck getCheck (Positionable parent) {
        return new NullFalseCheck();
    }

}

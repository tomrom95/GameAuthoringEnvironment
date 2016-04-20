package engine.definitions.upgrades;

import engine.ICheck;
import engine.Positionable;
import engine.modules.NullUpgradeModule;
import engine.modules.UpgradeModule;


public class NullUpgradeDefinition extends UpgradeDefinition {

    public UpgradeModule create (Positionable parent) {
        return new NullUpgradeModule(getGame(), getUpgrade(), getCheck(parent), parent);
    }
    
    @Override
    protected ICheck getCheck (Positionable parent) {
        return null;
    }

}

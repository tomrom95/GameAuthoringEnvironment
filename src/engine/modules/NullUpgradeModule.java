package engine.modules;

import engine.IAdder;
import engine.ICheck;
import engine.Positionable;
import engine.definitions.SpriteDefinition;

public class NullUpgradeModule extends UpgradeModule {

    public NullUpgradeModule (IAdder adder,
                              SpriteDefinition upgrade,
                              ICheck check,
                              Positionable parent) {
        super(adder, upgrade, check, parent);
    }
    
    @Override
    public boolean isUpdateable () {
        return false;
    }

}

package engine.modules;

import engine.IAdder;
import engine.ICheck;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class NullUpgradeModule extends UpgradeModule {

    public NullUpgradeModule (IAdder adder,
                              SpriteDefinition upgrade,
                              ICheck check,
                              Positionable parent) {
        super(adder, upgrade, check, parent);
    }
    
    @Override
    public BooleanProperty isUgradeable () {
        return new SimpleBooleanProperty(false);
    }

}

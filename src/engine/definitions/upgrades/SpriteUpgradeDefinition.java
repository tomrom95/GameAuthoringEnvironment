package engine.definitions.upgrades;

import engine.AttributeType;
import engine.Check;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import engine.modules.IModule;
import engine.modules.UpgradeModule;


public class SpriteUpgradeDefinition extends UpgradeDefinition {

    public SpriteUpgradeDefinition (SpriteDefinition upgrade, AttributeType type, double cost) {
        super(upgrade, type, cost);

    }

    @Override
    public IModule create (Positionable parent) {
        Check check = new Check(parent.getAttributeManager(), getType(), getCost());
        return new UpgradeModule(getUpgrade(), check);
    }
}

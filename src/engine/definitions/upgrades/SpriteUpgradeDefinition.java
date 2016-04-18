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

    public SpriteUpgradeDefinition (IGame game, SpriteDefinition upgrade, AttributeType type, double cost) {
        super(game, upgrade, type, cost);

    }

    @Override
    public IModule create (Positionable parent) {
        ICheck check = new Check(parent.getAttributeManager(), getType(), getCost());
        return new UpgradeModule(getGame(), getUpgrade(), check, parent);
    }
}

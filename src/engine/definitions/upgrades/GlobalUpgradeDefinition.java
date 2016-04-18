package engine.definitions.upgrades;

import engine.AttributeType;
import engine.Check;
import engine.IGame;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import engine.modules.IModule;
import engine.modules.UpgradeModule;


public class GlobalUpgradeDefinition extends UpgradeDefinition {

    private IGame myGame;

    public GlobalUpgradeDefinition (IGame game, SpriteDefinition upgrade, AttributeType type, double cost) {
        super(upgrade, type, cost);
        setGame(game);
    }

    private void setGame (IGame game) {
        myGame = game;
    }

    @Override
    public IModule create (Positionable parent) {
        Check check = new Check (myGame.getAttributeManager(), getType(), getCost());
        return new UpgradeModule(getUpgrade(), check);
    }

}

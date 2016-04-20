package engine.definitions.upgrades;

import engine.AttributeType;
import engine.Check;
import engine.IAdder;
import engine.ICheck;
import engine.IGame;
import engine.Positionable;
import engine.definitions.ModuleDefinition;
import engine.definitions.SpriteDefinition;
import engine.modules.IModule;
import engine.modules.UpgradeModule;

/**
 * Used to generate the upgrade module of the Sprite
 * @author RyanStPierre
 *
 */
public abstract class UpgradeDefinition extends ModuleDefinition {

    private SpriteDefinition myUpgrade;
    private AttributeType myType;
    private double myCost;
    private IGame myGame;
    
    public void setParameters (IGame myGame,
                               SpriteDefinition nextUpgrade,
                               AttributeType type,
                               double cost) {
        setGame(myGame);
        setUpgrade(nextUpgrade);
        setType(type);
        setCost(cost);
    }

    private void setGame (IGame game) {
        myGame = game;
    }

    protected IGame getGame () {
        return myGame;
    }

    private void setCost (double cost) {
        myCost = cost;

    }

    public SpriteDefinition getUpgrade () {
        return myUpgrade;
    }

    public void setUpgrade (SpriteDefinition upgrade) {
        myUpgrade = upgrade;
    }

    private void setType (AttributeType type) {
        myType = type;
    }

    public double getCost () {
        return myCost;
    }

    public AttributeType getType () {
        return myType;
    }

    @Override
    public UpgradeModule create (Positionable parent) {
        return new UpgradeModule(getGame(), getUpgrade(), getCheck(parent), parent);
    }

    protected abstract ICheck getCheck (Positionable parent);


}

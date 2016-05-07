package engine.definitions.upgrades;

import engine.AttributeType;
import engine.ICheck;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import engine.modules.UpgradeModule;


/**
 * Used to generate the upgrade module of the Sprite
 *
 * @author RyanStPierre
 *
 */
public abstract class UpgradeDefinition extends ModuleDefinition {

    private SpriteDefinition myUpgrade;
    private double myCost;
    private IGame myGame;
    private boolean isUpgradable = true;
    private AttributeDefinition myAttribute;

    public UpgradeDefinition () {
        // Empty shell if desired
    }

    public UpgradeDefinition (IGame myGame,
                              SpriteDefinition nextUpgrade,
                              AttributeDefinition attribute,
                              double cost) {
        setParameters(myGame, nextUpgrade, attribute, cost);
    }

    public void setParameters (IGame myGame,
                               SpriteDefinition nextUpgrade,
                               AttributeDefinition attribute,
                               double cost) {
        setGame(myGame);
        setUpgrade(nextUpgrade);
        setAttribute(attribute);
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

    private void setAttribute (AttributeDefinition attribute) {
        myAttribute = attribute;
    }

    public AttributeDefinition getAttribute () {
        return myAttribute;
    }

    public SpriteDefinition getUpgrade () {
        return myUpgrade;
    }

    public void setUpgrade (SpriteDefinition upgrade) {
        myUpgrade = upgrade;
    }

    public double getCost () {
        return myCost;
    }

    public boolean isUpgradable () {
        return isUpgradable;
    }

    protected void setIsUpgradable (boolean isUpgradable) {
        this.isUpgradable = isUpgradable;
    }

    public AttributeType getType () {
        return myAttribute.getAttributeType();
    }

    @Override
    public UpgradeModule create (Positionable parent) {
        return new UpgradeModule(getGame(), getUpgrade(), getCheck(parent), parent);
    }

    protected abstract ICheck getCheck (Positionable parent);

}

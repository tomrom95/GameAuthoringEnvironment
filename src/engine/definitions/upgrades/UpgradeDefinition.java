package engine.definitions.upgrades;

import engine.AttributeType;
import engine.IAdder;
import engine.IGame;
import engine.definitions.ModuleDefinition;
import engine.definitions.SpriteDefinition;


public abstract class UpgradeDefinition extends ModuleDefinition {

    private SpriteDefinition myUpgrade;
    private AttributeType myType;
    private double myCost;
    private IGame myGame;

    public UpgradeDefinition (IGame adder, SpriteDefinition upgrade, AttributeType type, double cost) {
        setGame(adder);
        setUpgrade(upgrade);
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
}

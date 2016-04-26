package gameauthoring.creation.subforms;

import engine.AttributeType;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import engine.definitions.upgrades.GlobalUpgradeDefinition;
import engine.definitions.upgrades.NullUpgradeDefinition;
import engine.definitions.upgrades.SpriteUpgradeDefinition;
import engine.definitions.upgrades.UpgradeDefinition;
import gameauthoring.util.ErrorMessage;


public class UpgradeSFC implements ISubFormControllerSprite {

    private IUpgradeSFV mySFV;
    private IGame myGame;
    private UpgradeDefinition myGlobalUpgrade;
    private UpgradeDefinition mySpriteUpgrade;
    private double myInitialValue = 0;

    public UpgradeSFC (IGame game) {
        mySFV = new UpgradeSFV(game.getAuthorshipData());
        this.myGame = game;
        this.myGlobalUpgrade = new GlobalUpgradeDefinition();
        this.mySpriteUpgrade = new SpriteUpgradeDefinition();
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        try {
            if (mySFV.isUpgradableProperty().get()) {
                double cost =
                        Double.parseDouble(mySFV.getData().getValueProperty(mySFV.getMyCostKey())
                                .get());
                AttributeType type = new AttributeType(mySFV.getDepletedAttribute().getType());
                SpriteDefinition nextUpgrade = mySFV.getNextUpgrade();
                if (mySFV.isGlobalProperty().get()) {
                    myGlobalUpgrade.setParameters(myGame, nextUpgrade, type, cost);
                    item.setUpgrade(myGlobalUpgrade);
                    
                }
                else {
                    mySpriteUpgrade.setParameters(myGame, nextUpgrade, type, cost);
                    item.setUpgrade(mySpriteUpgrade);
                   
                }
            }
            else{
                item.setUpgrade(new NullUpgradeDefinition());
            }
        }
        catch (Exception e) {
            ErrorMessage err =
                    new ErrorMessage("Please fill in all fields associated with Upgrade");// TODO
                                                                                          // resource
                                                                                          // file
            err.showError();
        }
    }

    @Override
    public ISubFormView getSubFormView () {
        return mySFV;
    }

    @Override
    public void initializeFields () {
        mySFV.getData().set(mySFV.getMyCostKey(), String.valueOf(this.myInitialValue));
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        UpgradeDefinition upgrade = item.getUpgrade();
        //TODO: fix bad code. just doing it now to see if populating works
        // actually i'm gonna do the others and come back
        if(upgrade instanceof NullUpgradeDefinition) {
            mySFV.setIsUpgradable(false);
        }else if (upgrade instanceof GlobalUpgradeDefinition){
            
            
        }else if (upgrade instanceof SpriteUpgradeDefinition) {
            //mySFV.setNextUpgrade(upgrade.getUpgrade());
            
        }
        
    }

}

package gameauthoring.creation.subforms;

import engine.AttributeType;
import engine.IGame;
import engine.definitions.ModuleDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.upgrades.GlobalUpgradeDefinition;
import engine.definitions.upgrades.SpriteUpgradeDefinition;
import engine.definitions.upgrades.UpgradeDefinition;
import gameauthoring.util.ErrorMessage;


public class UpgradeSFC implements ISubFormControllerSprite {

    private UpgradeSFV mySFV;
    private IGame myGame;
    private UpgradeDefinition myGlobalUpgrade;
    private UpgradeDefinition mySpriteUpgrade;

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
                double cost = Double.parseDouble(mySFV.getData().getValueProperty(mySFV.getMyCostKey()).get());
                AttributeType type = new AttributeType(mySFV.getDepeltedAttribute().getType());
                SpriteDefinition nextUpgrade = mySFV.getNextUpgrade();
                if (mySFV.isGlobalProperty().get()) {
                    myGlobalUpgrade.setParameters(myGame, nextUpgrade, type, cost);
                    updateModule(item,this.mySpriteUpgrade,this.myGlobalUpgrade);
                }
                else {
                    mySpriteUpgrade.setParameters(myGame, nextUpgrade, type, cost);
                    updateModule(item, this.myGlobalUpgrade, this.mySpriteUpgrade);
                }
            }
        }
        catch (Exception e) {
            ErrorMessage err = new ErrorMessage("Please fill in all fields associated with Upgrade");//TODO resource file
            err.showError();
        }
    }

    private void updateModule(SpriteDefinition item, ModuleDefinition moduleToReplace, ModuleDefinition moduleToAdd){
        if(item.getModuleDefinitions().contains(moduleToReplace)){
            item.getModuleDefinitions().remove(moduleToReplace);
        }
        item.getModuleDefinitions().add(moduleToAdd);
    }

    @Override
    public ISubFormView getSubFormView () {
        return mySFV;
    }

    @Override
    public void initializeFields () {
        mySFV.getData().set(mySFV.getMyCostKey(), "0.0"); //TODO: resource file
    }

}

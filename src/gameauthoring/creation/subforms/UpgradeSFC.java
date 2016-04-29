package gameauthoring.creation.subforms;

import engine.IGame;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.upgrades.GlobalUpgradeDefinition;
import engine.definitions.upgrades.NullUpgradeDefinition;
import engine.definitions.upgrades.SpriteUpgradeDefinition;
import engine.definitions.upgrades.UpgradeDefinition;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.util.ErrorMessage;


public class UpgradeSFC implements ISubFormControllerSprite {

    private IUpgradeSFV mySFV;
    private IGame myGame;

    public UpgradeSFC (IGame game, DefinitionCollection<SpriteDefinition> nextUpgrade) {
        mySFV = new UpgradeSFV(game.getAuthorshipData(), nextUpgrade);
        myGame = game;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        try {
            if (mySFV.isUpgradableProperty().get()) {
                double cost = mySFV.getMyCost();
                AttributeDefinition attribute = mySFV.getDepletedAttribute();
                SpriteDefinition nextUpgrade = mySFV.getNextUpgrade();
                if (mySFV.isGlobalProperty().get()) {
                    item.setUpgrade(new GlobalUpgradeDefinition(myGame, nextUpgrade, attribute, cost));
                }
                else {
                    item.setUpgrade(new SpriteUpgradeDefinition(myGame, nextUpgrade, attribute, cost));
                }
            }
            else {
                item.setUpgrade(new NullUpgradeDefinition());
            }
        }
        catch (Exception e) {
            ErrorMessage err =
                    new ErrorMessage("Please fill in all fields associated with Upgrade");
            err.showError();
        }
    }

    @Override
    public ISubFormView getSubFormView () {
        return mySFV;
    }

    @Override
    public void initializeFields (SpriteDefinition item) {
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        UpgradeDefinition upgrade = item.getUpgrade();
        mySFV.populateWithData(upgrade.isUpgradable(), upgrade.getUpgrade(), upgrade.getAttribute(),
                               upgrade.getCost());
    }

}

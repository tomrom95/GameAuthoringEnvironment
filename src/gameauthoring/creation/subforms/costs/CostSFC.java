package gameauthoring.creation.subforms.costs;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.costs.Cost;
import engine.definitions.costs.ICost;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


public class CostSFC implements ISubFormControllerSprite {

    private ICostSFV myView;
    private IGame myGame;

    public CostSFC (IGame game) {
        myView = new CostSFV(game.getAuthorshipData());
        myGame = game;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        if (!myView.costChecked()) {
            item.setCost(null);
        }
        double amount = myView.getCost();        
        ICost cost = new Cost(myGame, myView.getSelectedAttribute(), amount);
        item.setCost(cost);
    }

    @Override
    public void initializeFields (SpriteDefinition item) {
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        ICost cost = item.getCost();
        myView.populateWithData(cost == null, cost.getAttributeDefinition(), cost.getCostAmount());
    }

}

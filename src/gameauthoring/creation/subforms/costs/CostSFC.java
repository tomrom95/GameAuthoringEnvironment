package gameauthoring.creation.subforms.costs;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.costs.Cost;
import engine.definitions.costs.ICost;
import engine.definitions.costs.NullCost;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.ErrorMessage;


public class CostSFC implements ISubFormControllerSprite {

    private ICostSFV myView;
    private IGame myGame;

    public CostSFC (IGame game) {
        myView = new CostSFV(game.getAuthorshipData());
        myGame = game;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        try {
            if (!myView.costChecked()) {
                item.setCost(new NullCost());
            }
            else {
                double amount = myView.getCost();
                ICost cost = new Cost(myGame, myView.getSelectedAttribute(), amount);
                item.setCost(cost);
            }
        }
        catch (NullPointerException e) {
            ErrorMessage err = new ErrorMessage("Please Complete All Fields Associated with Cost");
            err.showError();
        }
    }

    @Override
    public void initializeFields () {
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        ICost cost = item.getCost();
        myView.populateWithData(cost.hasCost(), cost.getAttributeDefinition(),
                                cost.getCostAmount());
    }

}

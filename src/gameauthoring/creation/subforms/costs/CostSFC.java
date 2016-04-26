package gameauthoring.creation.subforms.costs;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.costs.Cost;
import engine.definitions.costs.ICost;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

public class CostSFC implements ISubFormControllerSprite{
    private static final int START_COST = 0;
    
    private CostSFV myView;
    private IGame myGame;
    
    public CostSFC (IGame game) {
        myView = new CostSFV(game.getAuthorshipData());
        myGame = game;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        double amount = Double.valueOf(myView.getData().getValueProperty(myView.getCostKey()).get());
        ICost cost = new Cost(myGame, myView.getSelectedAttribute(), amount);
        item.setCost(cost);
    }

    @Override
    public void initializeFields () {
        myView.getData().getValueProperty(myView.getCostKey()).set(String.valueOf(START_COST));
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        ICost cost = item.getCost();
        myView.setSelectedAttribute(cost.getAttributeDefinition());
        myView.getData().getValueProperty(myView.getCostKey()).set(Double.toString(cost.getCostAmount()));
        
    }

}

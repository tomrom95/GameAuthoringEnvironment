package gameauthoring.conditiontab;


import engine.IGame;
import engine.conditions.ICondition;
import javafx.collections.ObservableList;

public class OnGlobalPopUp extends ConditionPopUp {

    private IGame myGame;
    
    public OnGlobalPopUp (IGame game) {
        
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        initStage();
    }

    @Override
    protected void initializeDisplay () {
        // TODO Auto-generated method stub

    }

    @Override
    protected ICondition createCondition () {
        // TODO Auto-generated method stub
        return null;
    }

}

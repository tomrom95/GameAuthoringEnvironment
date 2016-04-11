package gameauthoring.conditiontab;

import engine.ICondition;
import engine.IGame;
import javafx.collections.ObservableList;


public class ConditionPopUpFactory {

    private IGame myGame;

    public ConditionPopUpFactory (IGame game) {
        myGame = game;
    }

    public ConditionPopUp get (String selection, ObservableList<ICondition> list) {
        
        //TODO replace with reflection
        switch(selection) {
            case "OnClickCondition": 
                return new OnClickPopUp(list, myGame);
            case "OnCollisionCondition": 
                return new OnCollisionPopUp(list, myGame);
            case "OnGlobalAttributeCondition": 
                return new OnGlobalPopUp(list, myGame);
            case "OnSpriteAttributeCondition": 
                return new OnSpritePopUp(list, myGame);
        }
        
        return null;
    }
}

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
        return new OnClickPopUp(list, myGame);
    }
}

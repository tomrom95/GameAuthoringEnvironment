package gameauthoring.listdisplay;

import engine.IGame;
import engine.ILevel;
import javafx.scene.layout.Pane;


public class LevelConditionController extends ConditionController {

   private ConditionViewFactory myFactory;

    public LevelConditionController (LevelConditionView conditionView, IGame game, ILevel level) {
        super(conditionView);
        myFactory = new ConditionViewFactory(game, level);

    }

    @Override
    protected ConditionViewFactory getFactory () {
        return myFactory;
    }

}

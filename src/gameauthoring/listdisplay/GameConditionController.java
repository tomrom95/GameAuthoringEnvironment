package gameauthoring.listdisplay;

import engine.IGame;
import javafx.scene.layout.Pane;


/**
 * Class name condition controller taken
 *
 * @author RyanStPierre
 *
 */
public class GameConditionController extends ConditionController {
    
    private ConditionViewFactory myFactory;

    public GameConditionController (ConditionView conditionView, IGame game) {
        super(conditionView);
        myFactory = new ConditionViewFactory(game);
    }

    @Override
    protected ConditionViewFactory getFactory () {
        return myFactory;
    }

}

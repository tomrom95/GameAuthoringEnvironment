package gameauthoring.listdisplay;

import engine.IGame;


/**
 * Class name condition controller taken
 *
 * @author RyanStPierre
 *
 */
public class GameConditionController extends ConditionController {
    
    private ConditionViewFactory myFactory;

    public GameConditionController (ConditionViewer conditionView, IGame game) {
        super(conditionView);
        myFactory = new ConditionViewFactory(game);
    }

    @Override
    protected ConditionViewFactory getFactory () {
        return myFactory;
    }

}

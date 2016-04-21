package gameauthoring.listdisplay;

import engine.IGame;
import javafx.scene.layout.Pane;


/**
 * Class name condition controller taken
 *
 * @author RyanStPierre
 *
 */
public class GameConditionController {

    private GameConditionView myView;

    private ConditionViewFactory myFactory;

    public GameConditionController (GameConditionView conditionView, IGame game) {
        myView = conditionView;
        myFactory = new ConditionViewFactory(game);

        setActions();

    }

    private void setActions () {

       myView.applyToOptions(e -> myView.populate(createPopUp(myView.getSelection())));

    }

    private Pane createPopUp (String selection) {
        return myFactory.get(selection)
                .show();
    }

}

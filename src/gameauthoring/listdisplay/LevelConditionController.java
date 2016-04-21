package gameauthoring.listdisplay;

import engine.IGame;
import engine.ILevel;
import javafx.scene.layout.Pane;


public class LevelConditionController {

    private LevelConditionView myView;
    private ConditionViewFactory myFactory;

    public LevelConditionController (LevelConditionView conditionView, IGame game, ILevel level) {
        myView = conditionView;
        myFactory = new ConditionViewFactory(game, level);
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

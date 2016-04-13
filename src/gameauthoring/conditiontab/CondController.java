package gameauthoring.conditiontab;

import engine.IGame;
import engine.conditions.OnClickCondition;
import javafx.scene.layout.Pane;


/**
 * Class name condition controller taken
 * 
 * @author RyanStPierre
 *
 */
public class CondController {

    private ConditionView myView;
    
    private ConditionPopUpFactory myFactory;

    public CondController (ConditionView conditionView, IGame game) {
        myView = conditionView;
        myFactory = new ConditionPopUpFactory(game);
      
        setActions();

    }

    private void setActions () {

        myView.applyToButton(e -> myView.setCenter(createPopUp(myView.getComboSelection())));

    }

    private Pane createPopUp (String selection) {
        return myFactory.get(selection)
                .show();
    }

}

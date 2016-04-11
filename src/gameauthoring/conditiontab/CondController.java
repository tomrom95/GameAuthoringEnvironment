package gameauthoring.conditiontab;

import engine.IGame;
import javafx.stage.Popup;


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
       
        myView.getButton().setOnAction(e-> createPopUp(myView.getBox().getSelectionModel().getSelectedItem()));
       
        
    }

    private void createPopUp (String selection) {
        myFactory.get(selection, myView.getList()).show();
    }

}

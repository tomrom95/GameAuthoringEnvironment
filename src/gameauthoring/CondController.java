package gameauthoring;

import javafx.stage.Popup;


/**
 * Class name condition controller taken
 * 
 * @author RyanStPierre
 *
 */
public class CondController {

    private ConditionView myView;
    private IPopUpFactory myFactory;

    public CondController (ConditionView conditionView) {
        myView = conditionView;
        myFactory = new ConditionPopUpFactory();
        setActions();

    }

    private void setActions () {
       
        myView.getButton().setOnAction(e-> createPopUp(myView.getBox().getSelectionModel().getSelectedItem()));
       
        
    }

    private void createPopUp (String selection) {
        
    }

}

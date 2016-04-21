package gameauthoring.listdisplay;

import javafx.scene.Node;

public abstract class ConditionController {

    private ConditionView myView;
    
    public ConditionController (ConditionView conditionView) {
        myView = conditionView;
        setActions();

    }

    private void setActions () {
        myView.applyToOptions(e -> myView.populate(createPopUp(myView.getSelection())));
        
    }

    private Node createPopUp (String selection) {
        return getFactory().get(selection)
                .show();
    }

    protected abstract ConditionViewFactory getFactory ();

}
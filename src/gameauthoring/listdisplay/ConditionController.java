package gameauthoring.listdisplay;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;
import gameauthoring.util.ErrorMessage;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


public abstract class ConditionController {

    private ResourceBundle myLanguage =
            ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);
    private ConditionView myView;

    public ConditionController (ConditionView conditionView) {
        myView = conditionView;
        setActions();

    }

    private void setActions () {
        myView.applyToOptions(e -> myView.populate(createPopUp(myView.getSelection())));

    }

    /**
     * If the factory fails show message and display empty pane
     * 
     * @param selection
     * @return
     */
    private Node createPopUp (String selection) {
        try {
            return getFactory().get(selection)
                    .show();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | SecurityException e) {
            ErrorMessage error = new ErrorMessage(myLanguage.getString("ConditionViewFile"));
            error.showError();
            return new Pane();
        }
    }

    protected abstract ConditionViewFactory getFactory ();

}

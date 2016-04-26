package gameauthoring.listdisplay;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;
import gameauthoring.util.ErrorMessage;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;


public abstract class ConditionController {

    private static final double Y_OFFSET = 1/2;
    private static final double X_OFFSET = 1/4;
    
    private ResourceBundle myLanguage =
            ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);
    private ConditionView myView;

    public ConditionController (ConditionView conditionView) {
        myView = conditionView;
        setActions();

    }

    private void setActions () {
        myView.applyToOptions(e -> myView.populate(createPopUp(myView.getSelection())));
        myView.getEditor().setOnMouseEntered(e -> showHelpStatement());

    }

    private void showHelpStatement () {
        Tooltip help = new Tooltip(myLanguage.getString("CondHelp"));
        help.show(myView.getEditor(), getPoint().getX(), getPoint().getY());
        myView.getEditor().setOnMouseExited(e -> help.hide());
    }
    
    private Point2D getPoint () {
        return myView.getEditor().localToScreen(myView.getEditor().getLayoutBounds().getMaxX() * X_OFFSET,
                                      myView.getEditor().getLayoutBounds().getMaxY() * Y_OFFSET);
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

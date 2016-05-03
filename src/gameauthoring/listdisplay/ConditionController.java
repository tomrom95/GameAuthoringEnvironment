package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import gameauthoring.creation.factories.ReflectionException;
import gameauthoring.util.ErrorMessage;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import splash.LocaleManager;


/**
 * Responsible for setting the action of the ConditionView
 * Main responsibility is setting the button action to generate the proper sub-view "pop-up" when
 * interacted
 * Factory call is done internally
 *
 * @author RyanStPierre
 *
 */

public abstract class ConditionController {

    /**
     * Offset for the tool-tip
     */
    private ResourceBundle myBundle =
            ResourceBundle.getBundle("defaults/tool_tip");
    private ResourceBundle myLanguage =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private ConditionViewer myView;

    public ConditionController (ConditionViewer conditionView) {
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
        return myView.getEditor()
                .localToScreen(myView.getEditor().getLayoutBounds().getMaxX() *
                               Double.parseDouble(myBundle.getString("xOffset")),
                               myView.getEditor().getLayoutBounds().getMaxY() * Double
                                       .parseDouble(myBundle.getString("yOffset")));
    }

    /**
     * If the factory fails show message and display empty pane
     *
     * @param selection
     * @return
     */
    private Node createPopUp (String selection) {
        try {
            return getFactory().interpret(selection)
                    .show();
        }
        catch (ReflectionException e) {
            ErrorMessage error = new ErrorMessage(myLanguage.getString("ConditionViewFile"));
            error.showError();
            e.printStackTrace();
            return new Pane();
        }
    }

    /**
     * Subclasses of this controller class need to instantiate the factory differently, depending on
     * whether or not access to the level is necessary
     *
     * @return
     */

    protected abstract ConditionViewFactory getFactory ();

}

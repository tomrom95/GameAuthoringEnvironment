package gameauthoring.creation.subforms.movement;

import java.util.ResourceBundle;
import gameauthoring.creation.subforms.SubFormView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import splash.LocaleManager;


/**
 * View representing a sub form that creates the information required to build a static mover
 * module
 *
 * @author Joe Lilien
 * @author Dhrumil
 *
 */
public class StaticMoverSFV extends SubFormView {
    private Label myStaticMoverLabel;
    private ResourceBundle myLabel;

    public StaticMoverSFV () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myStaticMoverLabel = new Label(myLabel.getString("StaticSprite"));
        myStaticMoverLabel.getStyleClass().add("staticLabel");
    }

    @Override
    public Node draw () {
        return myStaticMoverLabel;
    }

    @Override
    protected void initView () {

    }
}

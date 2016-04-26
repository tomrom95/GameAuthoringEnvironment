package gameauthoring.creation.subforms.movement;

import gameauthoring.creation.subforms.SubFormView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


/**
 * View representing a sub form that creates the information required to build a static mover
 * module
 * 
 * @author Joe Lilien
 * @author Dhrumil
 *
 */
public class StaticMoverSFV extends SubFormView {
    private Label myLabel = new Label("STATIC SPRITE");
    
    public StaticMoverSFV () {
        myLabel.getStyleClass().add("staticLabel");
    }

    @Override
    public Node draw () {
        return myLabel;
    }

    @Override
    protected void initView () {

    }
}

package gameauthoring.creation.subforms.movement;

import gameauthoring.creation.subforms.SubFormView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * View representing a sub form that creates the information required to build a static mover
 * module
 * 
 * @author Dhrumil
 *
 */
public class StaticMoverSubFormView extends SubFormView {

    private GridPane myPane = new GridPane();

    @Override
    public Node draw () {
        return myPane;
    }

    public void initView () {

    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }

}

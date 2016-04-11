package gameauthoring;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;


/**
 * Level tab view class which allows the user to put together all the sprites in different levels
 * Handles selection for different level editor views.
 * 
 * @author Jin An
 *
 */
public class SceneTabViewer implements ITabViewer {

    // Has multiple LevelEditorViews

    private BorderPane myLayout;

    public SceneTabViewer () {
    }

    @Override
    public Node draw () {
        myLayout = new BorderPane();
        return myLayout;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }
}

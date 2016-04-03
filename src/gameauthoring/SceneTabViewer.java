package gameauthoring;

import javafx.scene.Node;
import javafx.scene.control.Tab;


/**
 * Level tab view class which allows the user to put together all the sprites in different levels
 * Handles selection for different level editor views.
 * 
 * @author Jin An
 *
 */
public class SceneTabViewer implements ITabViewer {

    // Has multiple LevelEditorViews

    private Tab mySceneTab;

    public SceneTabViewer (Tab sceneTab) {
        mySceneTab = sceneTab;
    }

    @Override
    public Node draw () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }
}

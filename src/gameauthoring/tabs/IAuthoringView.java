package gameauthoring.tabs;

import javafx.stage.Stage;


/**
 * The main view class for the authoring environment. This will create the game and handle the
 * selection between different parts of the authoring * environment.
 *
 * @author Tommy
 * @author Jin An
 * @author Dhrumil
 *
 */
public interface IAuthoringView {

    /**
     * Creates initial tabs/views that make up the main view
     */
    void init (Stage s);
}

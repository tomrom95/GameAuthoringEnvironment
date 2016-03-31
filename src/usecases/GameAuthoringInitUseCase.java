package usecases;

import gameauthoring.AuthoringView;
import gameauthoring.CharTabViewer;
import gameauthoring.LevelTabViewer;
import gameauthoring.TabViewer;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


/**
 * This use case shows how the highest level view of the game authoring environment
 * 
 * It is split up into 2 main tabs: one for character (sprite) creation, and one
 * for level creation
 * 
 * @author Jeremy Schreck
 *
 */
public class GameAuthoringInitUseCase implements AuthoringView {

    public GameAuthoringInitUseCase () {

    }

    @Override
    public void init () {
        TabViewer charTabView = new CharTabViewer();
        TabViewer levelTabView = new LevelTabViewer();

        TabPane tabPane = new TabPane();

        Tab charTab = new Tab();
        Tab levelTab = new Tab();

        charTab.setContent(charTabView.draw());
        levelTab.setContent(levelTabView.draw());

        tabPane.getTabs().add(charTab);
        tabPane.getTabs().add(levelTab);

    }

}

package gameauthoring;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;


public class AuthoringView implements IAuthoringView {

    private GameTabViewer myGameTabViewer;
    private CharTabViewer myCharTabViewer;
    private SceneTabViewer mySceneTabViewer;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    @Override
    public GameTabViewer getGameTabViewer () {
        return myGameTabViewer;
    }

    @Override
    public CharTabViewer getCharTabViewer () {
        return myCharTabViewer;
    }

    @Override
    public SceneTabViewer getLevelTabViewer () {
        return mySceneTabViewer;
    }

    @Override
    public void init (Stage s) {
        TabPane tabPane = createAllTabs();
        s.setScene(new Scene(tabPane, WIDTH, HEIGHT));
    }

    private TabPane createAllTabs () {
        TabPane tabpane = new TabPane();
        myGameTabViewer = new GameTabViewer(createTab("Game"));
        myCharTabViewer = new CharTabViewer(createTab("Create Objects"));
        mySceneTabViewer = new SceneTabViewer(createTab("Build Scenes/Levels"));

        tabpane.getTabs().addAll(myGameTabViewer.getTab(), myCharTabViewer.getTab(),
                                 mySceneTabViewer.getTab());
        return tabpane;
    }

    private Tab createTab (String tabName) {
        Tab newTab = new Tab();
        newTab.setText(tabName);
        return newTab;
    }
}

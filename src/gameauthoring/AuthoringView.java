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
        TabPane tabPane = new TabPane();
        List<Tab> listOfTabs = createAllTabs();
        tabPane.getTabs().addAll(listOfTabs);
        s.setScene(new Scene(tabPane, WIDTH, HEIGHT));
    }

    private List<Tab> createAllTabs () {
        List<Tab> listOfTabs = new ArrayList<Tab>();
        Tab gameTab = createTab("Game");
        listOfTabs.add(gameTab);
        myGameTabViewer = new GameTabViewer(gameTab);
        
        Tab createObjectTab = createTab("Create Objects");
        listOfTabs.add(createObjectTab);
        myCharTabViewer = new CharTabViewer(createObjectTab);
        
        Tab buildSceneTab = createTab("Build Scenes/Levels");
        listOfTabs.add(buildSceneTab);
        mySceneTabViewer = new SceneTabViewer(buildSceneTab);
        
        return listOfTabs;
    }

    private Tab createTab (String tabName) {
        Tab newTab = new Tab();
        newTab.setText(tabName);
        return newTab;
    }
}

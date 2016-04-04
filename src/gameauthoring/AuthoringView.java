package gameauthoring;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
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
        MenuBar menuBar = createMenuBar();
        TabPane tabPane = createAllTabs();
        GridPane root = new GridPane();
        root.add(menuBar, 0, 0);
        root.add(tabPane, 0, 2);
        s.setScene(new Scene(root, WIDTH, HEIGHT));
    }

    private MenuBar createMenuBar () {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveItem = createMenuItems("Save as XML Files", null); // Connect with Save Actions
        fileMenu.getItems().add(saveItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private MenuItem createMenuItems (String itemName, ActionEvent action) {
        MenuItem newMenuItem = new MenuItem(itemName);
        newMenuItem.setOnAction(null);
        return newMenuItem;
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

package gameauthoring;

import data.GameWriter;
import data.IGameWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
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
    private ObjectCreationTabViewer myCreationTabViewer;
    private SceneTabViewer mySceneTabViewer;
    private GridPane myLayout;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    @Override
    public GameTabViewer getGameTabViewer () {
        return myGameTabViewer;
    }

    @Override
    public ObjectCreationTabViewer getCreationTabViewer () {
        return myCreationTabViewer;
    }

    @Override
    public SceneTabViewer getLevelTabViewer () {
        return mySceneTabViewer;
    }

    @Override
    public void init (Stage s) {
        MenuBar menuBar = createMenuBar();
        TabPane tabPane = createAllTabs();
        myLayout = new GridPane();
        myLayout.add(menuBar, 0, 0);
        myLayout.add(tabPane, 0, 2);
        Group root = new Group(myGameTabViewer.draw(), myCreationTabViewer.draw(),
                               mySceneTabViewer.draw());
        root.getChildren().addAll(myLayout);
        s.setScene(new Scene(root, WIDTH, HEIGHT));
    }

    private MenuBar createMenuBar () {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveItem = createMenuItems("Save your game as XML Files", e->writeGame()); //
        fileMenu.getItems().add(saveItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private MenuItem createMenuItems (String itemName, EventHandler<ActionEvent> e) {
        MenuItem newMenuItem = new MenuItem(itemName);
        newMenuItem.setOnAction(null);
        return newMenuItem;
    }

    private void writeGame (){
    }
    
    private TabPane createAllTabs () {
        TabPane tabpane = new TabPane();
        myGameTabViewer = new GameTabViewer(createTab("Game"));
        myCreationTabViewer = new ObjectCreationTabViewer(createTab("Create Objects"));
        mySceneTabViewer = new SceneTabViewer(createTab("Build Scenes/Levels"));

        tabpane.getTabs().addAll(myGameTabViewer.getTab(), myCreationTabViewer.getTab(),
                                 mySceneTabViewer.getTab());
        return tabpane;
    }

    private Tab createTab (String tabName) {
        Tab newTab = new Tab();
        newTab.setText(tabName);
        return newTab;
    }
}

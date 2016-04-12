package gameauthoring.tabs;

import data.GameWriter;
import data.IGameWriter;
import engine.Game;
import gameauthoring.SceneTabViewer;
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


/**
 * Highest hierarchy class for authoring environment. Used composition for each tab viewers
 * 
 * @author Jin An
 *
 */
public class AuthoringView implements IAuthoringView {

    private GameTabViewer myGameTabViewer;
    private ObjectCreationTabViewer myCreationTabViewer;
    private SceneTabViewer mySceneTabViewer;
    private GridPane myLayout;
    private Game myGame;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
   

    
    public AuthoringView() {
        GameFactory gameFactory = new GameFactory();
        myGame = gameFactory.createGame();
        
        
        
    }
    
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
        s.setScene(new Scene(myLayout, WIDTH, HEIGHT));
    }

    private MenuBar createMenuBar () {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveItem = createMenuItems("Save your game as XML Files", e -> saveToXML());
        fileMenu.getItems().add(saveItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    // TODO: Create GameWriter Class and save it as XML
    private void saveToXML () {

    }

    private MenuItem createMenuItems (String itemName, EventHandler<ActionEvent> action) {
        MenuItem newMenuItem = new MenuItem(itemName);
        newMenuItem.setOnAction(action);
        return newMenuItem;
    }

    private TabPane createAllTabs () {
        TabPane tabpane = new TabPane();

        Tab gameTab = createTab("Game");
        myGameTabViewer = new GameTabViewer(getMyGame());
        gameTab.setContent(myGameTabViewer.draw());

        Tab creationTab = createTab("Create Objects");
        myCreationTabViewer = new ObjectCreationTabViewer(getMyGame());
        creationTab.setContent(myCreationTabViewer.draw());

        Tab sceneTab = createTab("Build Scenes/Levels");
        mySceneTabViewer = new SceneTabViewer(getMyGame());
        sceneTab.setContent(mySceneTabViewer.draw());

        tabpane.getTabs().addAll(gameTab, creationTab, sceneTab);
        return tabpane;
    }

    private Tab createTab (String tabName) {
        Tab newTab = new Tab();
        newTab.setText(tabName);
        return newTab;
    }
    
    private Game getMyGame(){
        return myGame;
    }
}

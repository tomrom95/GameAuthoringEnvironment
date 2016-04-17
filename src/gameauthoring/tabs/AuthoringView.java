package gameauthoring.tabs;

import engine.Game;
import gameauthoring.conditiontab.ConditionView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Highest hierarchy class for authoring environment. Used composition for each tab viewers and
 * gameFactory. It creates a Menubar which has "Save game as XML" menu item and a tab pane which
 * contains "game information", "create objects", and "build scene" tabs. These are divided in order
 * for the users to easily create their own game.
 * 
 * TODO: Resourcebundle for unprotected string values
 * TODO: Create gamewriter class and save it as XML
 * @author Jin An
 *
 */

public class AuthoringView implements IAuthoringView {

    private GameTabViewer myGameTabViewer;
    private ObjectCreationTabViewer myCreationTabViewer;
    private SceneTabViewer mySceneTabViewer;
    private ConditionView myConditionView;
    private BorderPane myLayout;
    private Game myGame;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final String STYLESHEET = "custom.css";
    public static final String DEFAULT_RESOURCE_PACKAGE = "resource/";
    public static final String DEFAULT_ENTRYVIEW = "defaultTextEntry";


    public AuthoringView () {
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
        myLayout = new BorderPane();
        myLayout.setCenter(createContents());
        myLayout.setTop(createStatusBar());
        Scene scene = new Scene(myLayout, WIDTH, HEIGHT);
        scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE+STYLESHEET);
        s.setScene(scene);
    }

    private Node createStatusBar(){
        Image home = new Image("images/home-button.png", 50, 50, true, true);
        Image save = new Image("images/save-button.jpg", 50, 50, true, true);
        ImageView homeView = new ImageView(home);
        ImageView saveView = new ImageView(save);
        Button homeButton = new Button ("Home", homeView);
        Button saveButton = new Button ("Save", saveView);
        homeButton.setOnAction(e -> goHome());
        saveButton.setOnAction(e -> saveToXML());
        
        HBox statusBar = new HBox(10, homeButton, saveButton);
        return statusBar;
    }
    
    private Node createContents () {
//        MenuBar menuBar = createMenuBar();
        TabPane tabPane = createAllTabs();
        GridPane contents = new GridPane();
//        contents.add(menuBar, 0, 0);
        contents.add(tabPane, 0, 2);
        return contents;
    }
    private MenuBar createMenuBar () {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
//        MenuItem saveItem = createMenuItems("Save your game as XML Files", e -> saveToXML());
//        fileMenu.getItems().add(saveItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private void goHome () {
        
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
        gameTab.setClosable(false);
        gameTab.setContent(myGameTabViewer.draw());

        Tab creationTab = createTab("Create Objects");
        myCreationTabViewer = new ObjectCreationTabViewer(getMyGame());
        creationTab.setClosable(false);
        creationTab.setContent(myCreationTabViewer.draw());

        Tab conditionTab = createTab("Conditions");
        myConditionView = new ConditionView(getMyGame());
        conditionTab.setContent(myConditionView.draw());
        Tab sceneTab = createTab("Build Scenes/Levels");
        mySceneTabViewer = new SceneTabViewer(getMyGame());
        sceneTab.setClosable(false);
        sceneTab.setContent(mySceneTabViewer.draw());
        
        
        tabpane.getTabs().addAll(gameTab, creationTab, conditionTab, sceneTab);
        return tabpane;
    }

    private Tab createTab (String tabName) {
        Tab newTab = new Tab();
        newTab.setText(tabName);
        return newTab;
    }

    private Game getMyGame () {
        return myGame;
    }
}

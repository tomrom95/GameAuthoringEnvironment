package gameauthoring.tabs;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.IGame;
import gameauthoring.listdisplay.GameConditionView;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.waves.WaveTabViewer;
import gameplayer.GamePlayer;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Highest hierarchy class for authoring environment. Used composition for each tab viewers and
 * gameFactory. It has Home and Save image buttons for users to easily navigate to home and save the
 * game as XML. It contains "game information", "create objects", and "build scene" tabs. These are
 * divided in order for the users to easily create their own game.
 * 
 * TODO: Support multiple languages
 * TODO: Create gamewriter class and save it as XML
 * TODO: Implement go Home button
 * TODO: Implement saveToXML method
 * 
 * @author Jin An
 *
 */

public class AuthoringView implements IAuthoringView {

    private GameTabViewer myGameTabViewer;
    private ObjectCreationTabViewer myCreationTabViewer;
    private SceneTabViewer mySceneTabViewer;
    private GameConditionView myConditionView;
    private WaveTabViewer myWaveTabView;
    private BorderPane myLayout;
    private IGame myGame;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final String STYLESHEET = "custom.css";
    public static final String DEFAULT_RESOURCE_PACKAGE = "defaults/";
    public static final String DEFAULT_ENTRYVIEW = "defaultTextEntry";
    private BasicUIFactory myUIFactory = new BasicUIFactory();
    public static final String HOME = "Home";
    public static final String SAVE = "Save";
    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);
    private ResourceBundle myImages = ResourceBundle.getBundle("defaults/authoringmenus");

    public AuthoringView () {
        GameFactory gameFactory = new GameFactory();
        myGame = gameFactory.createGame();
    }

    public AuthoringView (IGame game) {
        myGame = game;
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
        initializeTabViewers();
        myLayout = new BorderPane();
        myLayout.setCenter(createContents());
        myLayout.setTop(createMenuBar());
        Scene scene = new Scene(myLayout, WIDTH, HEIGHT);
        scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        s.setScene(scene);
    }

    private void initializeTabViewers () {
        myGameTabViewer = new GameTabViewer(getMyGame());
        myCreationTabViewer = new ObjectCreationTabViewer(getMyGame());
        myConditionView = new GameConditionView(getMyGame());
        mySceneTabViewer = new SceneTabViewer(getMyGame());
        myWaveTabView = new WaveTabViewer(getMyGame());
    }

    private MenuBar createMenuBar () {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveItem = createMenuItems("Save game", e -> saveToXML());
        MenuItem launchItem = createMenuItems("Launch game", e -> launchGame());
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(launchItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private MenuItem createMenuItems (String itemName, EventHandler<ActionEvent> action) {
        MenuItem newMenuItem = new MenuItem(itemName);
        newMenuItem.setOnAction(action);
        return newMenuItem;
    }
    // private Node createStatusBar () {
    // Image home = new Image(myImages.getString(HOME), 40, 40, true, true);
    // Image save = new Image(myImages.getString(SAVE), 40, 40, true, true);
    // ImageView homeView = new ImageView(home);
    // ImageView saveView = new ImageView(save);
    // Button homeButton = myUIFactory.createImageButton(HOME, homeView, e -> goHome());
    // Button saveButton = myUIFactory.createImageButton(SAVE, saveView, e -> saveToXML());
    //
    // HBox statusBar = new HBox(10, homeButton, saveButton);
    // return statusBar;
    // }

    private Node createContents () {
        TabPane tabPane = createAllTabs();
        GridPane contents = new GridPane();
        contents.add(tabPane, 0, 2);
        return contents;
    }

    private void goHome () {

    }

    private void saveToXML () {
        File f = getFile();
        
        
    }
    
    private void launchGame () {
        XStream xstream = new XStream(new DomDriver());
        FXConverters.configure(xstream);
        xstream.setMode(XStream.SINGLE_NODE_XPATH_RELATIVE_REFERENCES);
        myGame.createAndSortGlobals();

        String xml = xstream.toXML(myGame);
        IGame game = (IGame) xstream.fromXML(xml);
        GamePlayer player = new GamePlayer(game);
    }

    private File getFile () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(myLang.getString("SaveGame"));
        fileChooser.setInitialDirectory(new File("resources/saved_games"));
        return fileChooser.showSaveDialog(new Stage());
    }

    private TabPane createAllTabs () {
        TabPane tabpane = new TabPane();
        tabpane.getStyleClass().add("authoringTabs");
        Tab gameTab =
                myUIFactory.createTabGraphic(
                                             myUIFactory.makeImageDisplay("images/game.png",
                                                                          "GAME"),
                                             false, myGameTabViewer.draw());
        Tab creationTab =
                myUIFactory.createTabGraphic(myUIFactory.makeImageDisplay("images/tools.png",
                                                                          "OBJECT CREATOR"),
                                             false, myCreationTabViewer.draw());
        Tab conditionTab =
                myUIFactory.createTabGraphic(myUIFactory.makeImageDisplay("images/collision.png",
                                                                          "CONDITIONS"),
                                             false, myConditionView.draw());
        Tab waveTab =
                myUIFactory.createTabGraphic(
                                             myUIFactory.makeImageDisplay("images/waves.png",
                                                                          "WAVES"),
                                             false, myWaveTabView.draw());
        Tab sceneTab =
                myUIFactory.createTabGraphic(myUIFactory.makeImageDisplay("images/scene.png",
                                                                          "SCENE BUILDER"),
                                             false, mySceneTabViewer.draw());
        tabpane.getTabs().addAll(gameTab, creationTab, conditionTab, waveTab, sceneTab);

        return tabpane;
    }

    private IGame getMyGame () {
        return myGame;
    }
}

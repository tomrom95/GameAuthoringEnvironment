package gameauthoring.tabs;

import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.Game;
import engine.IGame;
import gameauthoring.listdisplay.GameConditionView;
import gameauthoring.util.UIFactory;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Highest hierarchy class for authoring environment. Used composition for each tab viewers and
 * gameFactory. It has Home and Save image buttons for users to easily navigate to home and save the
 * game as XML. It contains "game information", "create objects", and "build scene" tabs. These are
 * divided in order for the users to easily create their own game.
 * 
 * TODO: Resourcebundle for unprotected string values
 * TODO: Create gamewriter class and save it as XML
 * 
 * @author Jin An
 *
 */

public class AuthoringView implements IAuthoringView {

    private GameTabViewer myGameTabViewer;
    private ObjectCreationTabViewer myCreationTabViewer;
    private SceneTabViewer mySceneTabViewer;
    private GameConditionView myConditionView;
    private BorderPane myLayout;
    private IGame myGame;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final String STYLESHEET = "custom.css";
    public static final String DEFAULT_RESOURCE_PACKAGE = "defaults/";
    public static final String DEFAULT_ENTRYVIEW = "defaultTextEntry";
    private UIFactory myUIFactory = new UIFactory();
    
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
        myLayout.setTop(createStatusBar());
        Scene scene = new Scene(myLayout, WIDTH, HEIGHT);
        scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        s.setScene(scene);
    }

    private void initializeTabViewers () {
        myGameTabViewer = new GameTabViewer(getMyGame());
        myCreationTabViewer = new ObjectCreationTabViewer(getMyGame());
        myConditionView = new GameConditionView(getMyGame());
        mySceneTabViewer = new SceneTabViewer(getMyGame());
    }

    private Node createStatusBar () {
        Image home = new Image("images/home-button.png", 40, 40, true, true);
        Image save = new Image("images/save-button.jpg", 40, 40, true, true);
        ImageView homeView = new ImageView(home);
        ImageView saveView = new ImageView(save);
        Button homeButton = myUIFactory.createImageButton("Home", homeView, e -> goHome());
        Button saveButton = myUIFactory.createImageButton("Save", saveView, e -> saveToXML());
        
        HBox statusBar = new HBox(10, homeButton, saveButton);
        return statusBar;
    }

    private Node createContents () {
        TabPane tabPane = createAllTabs();
        GridPane contents = new GridPane();
        contents.add(tabPane, 0, 2);
        return contents;
    }

    // TODO: GoHome Button
    private void goHome () {

    }

    // TODO: Create GameWriter Class and save it as XML
    private void saveToXML () {

        XStream xstream = new XStream(new DomDriver());
        FXConverters.configure(xstream);
        xstream.setMode(XStream.SINGLE_NODE_XPATH_RELATIVE_REFERENCES);
        myGame.createAndSortGlobals();
        
        String xml = xstream.toXML(myGame);
        IGame game = (IGame) xstream.fromXML(xml);
        GamePlayer player = new GamePlayer(game);
    }

    private TabPane createAllTabs () {
        TabPane tabpane = new TabPane();
        tabpane.getStyleClass().add("authoringTabs");
        Tab gameTab = myUIFactory.createTabGraphic(myUIFactory.makeImageDisplay("images/plant.png", "Game"), false, myGameTabViewer.draw());
        Tab creationTab =
                myUIFactory.createTabText("Create Objects", false, myCreationTabViewer.draw());
        Tab conditionTab = myUIFactory.createTabText("Conditions", false, myConditionView.draw());
        Tab sceneTab = myUIFactory.createTabText("Build Scenes/Levels", false, mySceneTabViewer.draw());
        tabpane.getTabs().addAll(gameTab, creationTab, conditionTab, sceneTab);
        return tabpane;
    }

    private IGame getMyGame () {
        return myGame;
    }
}

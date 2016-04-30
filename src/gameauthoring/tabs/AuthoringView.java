package gameauthoring.tabs;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.IGame;
import gameauthoring.listdisplay.GameConditionView;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.ErrorMessage;
import gameauthoring.waves.WaveTabViewer;
import gameplayer.GamePlayer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import serialize.GameReader;
import serialize.GameWriter;
import serialize.LoadErrorException;
import splash.LocaleManager;
import splash.MainUserInterface;


/**
 * Highest hierarchy class for authoring environment. Used composition for each tab viewers and
 * gameFactory. It has Home and Save image buttons for users to easily navigate to home and save the
 * game as XML. It contains "game information", "create objects", and "build scene" tabs. These are
 * divided in order for the users to easily create their own game.
 * 
 * 
 * @author Jin An
 *
 */

public class AuthoringView implements IAuthoringView {

    private GameTabViewer myGameTabViewer;
    private CreationTabViewer myCreationTabViewer;
    private SceneTabViewer mySceneTabViewer;
    private GameConditionView myConditionView;
    private WaveTabViewer myWaveTabView;
    private BorderPane myLayout;
    private IGame myGame;
    private Stage myStage;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final String STYLESHEET = "custom.css";
    public static final String DEFAULT_RESOURCE_PACKAGE = "defaults/";
    public static final String DEFAULT_ENTRYVIEW = "defaultTextEntry";
    private BasicUIFactory myUIFactory = new BasicUIFactory();
    public static final String HOME = "Home";
    public static final String SAVE = "Save";
    private ResourceBundle myLabel;
    private ResourceBundle myImages = ResourceBundle.getBundle("defaults/authoringmenus"); // TODO:
                                                                                           // ResourceBundle
                                                                                           // default
                                                                                           // for
                                                                                           // images

    public AuthoringView () {
        setResourceBundle();
        GameFactory gameFactory = new GameFactory();
        myGame = gameFactory.createGame();
    }

    private void setResourceBundle () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager.getInstance()
                .getCurrentLocaleProperty().get());
    }

    public AuthoringView (IGame game) {
        myGame = game;
    }

    @Override
    public GameTabViewer getGameTabViewer () {
        return myGameTabViewer;
    }

    @Override
    public CreationTabViewer getCreationTabViewer () {
        return myCreationTabViewer;
    }

    @Override
    public SceneTabViewer getLevelTabViewer () {
        return mySceneTabViewer;
    }

    @Override
    public void init (Stage s) {
        myStage = s;
        initializeTabViewers();
        myLayout = new BorderPane();
        myLayout.setCenter(createContents());
        myLayout.setTop(createMenuBar());
        Scene scene = new Scene(myLayout, WIDTH, HEIGHT);
        scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        s.setScene(scene);
        initListeners(s);
        rescale(s.getWidth(), s.getHeight());
    }

    private void initListeners (Stage stage) {
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed (ObservableValue<? extends Number> observableValue,
                                 Number oldSceneWidth,
                                 Number newSceneWidth) {
                rescale(newSceneWidth.doubleValue(), stage.getHeight());
            }
        });

        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed (ObservableValue<? extends Number> observableValue,
                                 Number oldSceneHeight,
                                 Number newSceneHeight) {
                rescale(stage.getWidth(), newSceneHeight.doubleValue());
            }
        });

    }

    private void rescale (double width, double height) {
        mySceneTabViewer.rescale(width, height);
    }

    private void initializeTabViewers () {
        myGameTabViewer = new GameTabViewer(getMyGame());
        myCreationTabViewer = new CreationTabViewer(getMyGame());
        myConditionView = new GameConditionView(getMyGame());
        mySceneTabViewer = new SceneTabViewer(getMyGame());
        myWaveTabView = new WaveTabViewer(getMyGame());
    }

    private MenuBar createMenuBar () {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu(myLabel.getString("File"));
        MenuItem goHome = createMenuItems(myLabel.getString("GoHome"), e -> goHome());
        MenuItem saveItem = createMenuItems(myLabel.getString("Save"), e -> saveToXML());
        MenuItem loadItem = createMenuItems(myLabel.getString("Load"), e -> loadGame());
        MenuItem launchItem = createMenuItems(myLabel.getString("Launch"), e -> launchGame());
        fileMenu.getItems().add(goHome);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(loadItem);
        fileMenu.getItems().add(launchItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private MenuItem createMenuItems (String itemName, EventHandler<ActionEvent> action) {
        MenuItem newMenuItem = new MenuItem(itemName);
        newMenuItem.setOnAction(action);
        return newMenuItem;
    }

    private Node createContents () {
        TabPane tabPane = createAllTabs();
        GridPane contents = new GridPane();
        contents.add(tabPane, 0, 2);
        return contents;
    }

    private void goHome () {
        new MainUserInterface().init(myStage);
    }

    private void saveToXML () {
        File f = saveFile();
        myGame.createAndSortGlobals();
        if (f != null) {
            try {
                new GameWriter().serialize(f, myGame);
            }
            catch (IOException e) {
                ErrorMessage message = new ErrorMessage(e.getMessage());
                message.showError();
            }
        }

    }

    private void loadGame () {
        try {
            IGame loadedGame = new GameReader().readFile(getFile());
            AuthoringView aView = new AuthoringView(loadedGame);
            Stage authoringStage = new Stage();
            aView.init(authoringStage);
            authoringStage.show();
        }
        catch (LoadErrorException e) {
            ErrorMessage message = new ErrorMessage(e.getMessage());
            message.showError();
        }
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

    private File saveFile () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(myLabel.getString("SaveGame"));
        fileChooser.setInitialDirectory(new File("resources/saved_games"));
        return fileChooser.showSaveDialog(new Stage());
    }
    
    private File getFile () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(myLabel.getString("Load"));
        fileChooser.setInitialDirectory(new File("resources/saved_games"));
        return fileChooser.showOpenDialog(new Stage());
    }

    private TabPane createAllTabs () {
        TabPane tabpane = new TabPane();
        tabpane.getStyleClass().add("authoringTabs");
        Tab gameTab =
                myUIFactory
                        .createTabGraphic(
                                          myUIFactory.makeImageDisplay("images/game.png",
                                                                       myLabel.getString("Game")),
                                          false, myGameTabViewer.draw());
        Tab creationTab =
                myUIFactory
                        .createTabGraphic(myUIFactory.makeImageDisplay("images/tools.png",
                                                                       myLabel.getString("CreateObjects")),
                                          false, myCreationTabViewer.draw());
        Tab conditionTab =
                myUIFactory
                        .createTabGraphic(myUIFactory.makeImageDisplay("images/collision.png",
                                                                       myLabel.getString("Conditions")),
                                          false, myConditionView.draw());
        Tab waveTab =
                myUIFactory
                        .createTabGraphic(
                                          myUIFactory.makeImageDisplay("images/waves.png",
                                                                       myLabel.getString("Waves")),
                                          false, myWaveTabView.draw());
        Tab sceneTab =
                myUIFactory
                        .createTabGraphic(myUIFactory.makeImageDisplay("images/scene.png",
                                                                       myLabel.getString("BuildScenes")),
                                          false, mySceneTabViewer.draw());
        tabpane.getTabs().addAll(gameTab, creationTab, conditionTab, waveTab, sceneTab);

        return tabpane;
    }

    private IGame getMyGame () {
        return myGame;
    }
}

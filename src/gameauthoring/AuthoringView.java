package gameauthoring;

import java.util.ResourceBundle;
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
    private ResourceBundle myResources;
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
        myResources = ResourceBundle.getBundle("resource/MainAuthoringTab");
        s.setScene(new Scene(myLayout, WIDTH, HEIGHT));
    }

    private MenuBar createMenuBar () {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu(myResources.getString("file"));
        MenuItem saveItem = createMenuItems(myResources.getString("save"), e -> saveToXML());
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

        Tab gameTab = createTab(myResources.getString("game"));
        myGameTabViewer = new GameTabViewer();
        gameTab.setContent(myGameTabViewer.draw());

        Tab creationTab = createTab(myResources.getString("object"));
        myCreationTabViewer = new ObjectCreationTabViewer();
        creationTab.setContent(myCreationTabViewer.draw());

        Tab sceneTab = createTab(myResources.getString("scene"));
        mySceneTabViewer = new SceneTabViewer();
        sceneTab.setContent(mySceneTabViewer.draw());

        tabpane.getTabs().addAll(gameTab, creationTab, sceneTab);
        return tabpane;
    }

    private Tab createTab (String tabName) {
        Tab newTab = new Tab();
        newTab.setText(tabName);
        return newTab;
    }
}

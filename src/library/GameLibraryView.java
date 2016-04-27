package library;

import engine.IGame;
import gameauthoring.tabs.AuthoringView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import splash.MainUserInterface;


public class GameLibraryView {

    private static final String CSS_FILE = "defaults/library.css";
    
    private Stage myStage;
    private BorderPane myLayout = new BorderPane();
    private MenuItem myHomeItem;
    private FlowPane myGameDisplay;

    public GameLibraryView () {
        createDisplay();
    }

    private void createDisplay () {
        createMenuBar();
        createGameDisplay();
    }

    private void createGameDisplay () {
        myGameDisplay = new FlowPane();
        myGameDisplay.setVgap(60);
        myGameDisplay.setHgap(100);
        myGameDisplay.setId("gamedisplay");
        myLayout.setCenter(myGameDisplay);
    }

    private void createMenuBar () {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        myHomeItem = new MenuItem("Go Home");
        myHomeItem.setOnAction(e -> new MainUserInterface().init(myStage));

        menu.getItems().add(myHomeItem);
        menuBar.getMenus().add(menu);
        myLayout.setTop(menuBar);
    }

    public void addToHomeButton (EventHandler<ActionEvent> event) {
        myHomeItem.setOnAction(event);
    }

    public void addGameToDisplay (Node gameDisp) {
        myGameDisplay.getChildren().add(gameDisp);
    }

    public void init (Stage s) {
        myStage = s;
        Scene scene = new Scene(myLayout, AuthoringView.WIDTH, AuthoringView.HEIGHT);
        scene.getStylesheets().add(CSS_FILE);
        s.setTitle("Game Library");
        s.setScene(scene);
    }

}

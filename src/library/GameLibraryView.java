package library;

import java.util.ResourceBundle;
import gameauthoring.tabs.AuthoringView;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import splash.LocaleManager;
import splash.MainUserInterface;


public class GameLibraryView {

    private static final String CSS_FILE = "defaults/library.css";
    private static final ResourceBundle myBundle =
            ResourceBundle.getBundle("defaults/game_library");
    private static final ResourceBundle myLang =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());

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
        myGameDisplay.setVgap(Double.parseDouble(myBundle.getString("Vgap")));
        myGameDisplay.setHgap(Double.parseDouble(myBundle.getString("Hgap")));
        myGameDisplay.setId(myBundle.getString("gamedisplayID"));
        myLayout.setCenter(myGameDisplay);
    }

    private void createMenuBar () {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu(myLang.getString("File"));
        myHomeItem = new MenuItem(myLang.getString("GoHome"));
        myHomeItem.setOnAction(e -> new MainUserInterface().init(myStage));

        menu.getItems().add(myHomeItem);
        menuBar.getMenus().add(menu);
        myLayout.setTop(menuBar);
    }

    public void addToHomeButton (EventHandler<ActionEvent> event) {
        myHomeItem.setOnAction(event);
    }

    public void addGameToDisplay (Node gameDisp) {
        FadeTransition fade = new FadeTransition(Duration.seconds(2), gameDisp);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        myGameDisplay.getChildren().add(gameDisp);
    }

    public void init (Stage s) {
        myStage = s;
        Scene scene = new Scene(myLayout, AuthoringView.WIDTH, AuthoringView.HEIGHT);
        scene.getStylesheets().add(CSS_FILE);
        s.setTitle(myLang.getString("GameLibrary"));
        s.setScene(scene);
    }

}

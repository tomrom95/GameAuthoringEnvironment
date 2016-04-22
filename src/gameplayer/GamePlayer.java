package gameplayer;

import java.util.ResourceBundle;
import engine.IGame;
import engine.IOInterpeter;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * This class handles the display of a game, and allows for basic high-level interaction from a
 * user.
 * 
 * @author David Maydew
 *
 */
public class GamePlayer {
    
    

    public static final double PREFWIDTH = 1250;
    public static final double PREFHEIGHT = 600;
    public static final int INT_PREF_WIDTH = (int) PREFWIDTH;
    public static final int INT_PREF_HEIGHT = (int) PREFHEIGHT;
    
    private static final String PATH = "defaults/gameplayer";
    ResourceBundle myBundle = ResourceBundle.getBundle(PATH);

    private Stage myStage = new Stage();
    private BorderPane myPane = new BorderPane();
    private Pane myLevelPane = new Pane();
    private Scene myScene = new Scene(myPane);
    private IGameEngine myGameEngine;

    public GamePlayer (IGame game) {
        setSizes();
        initializeGameEngine(game);
        stylePane();
    }

    private void setSizes () {
        myPane.setPrefSize(parseDouble(myBundle.getString("Width")),
                           parseDouble(myBundle.getString("Height")));
        myLevelPane.setPrefSize(parseDouble(myBundle.getString("LevelWidth")), 
                                parseDouble(myBundle.getString("LevelHeight")));

    }

    private void stylePane () {

        myStage.setScene(myScene);
        myStage.show();
    }

    private void initializeGameEngine (IGame game) {
        IOInterpeter IO = new IOInterpeter(myScene, myLevelPane);
        myGameEngine = new GameEngine(game, myPane, myLevelPane, IO);
    }

    public void play () {
        getGameEngine().play();
    }

    public void pause () {
        getGameEngine().pause();
    }

    private IGameEngine getGameEngine () {
        return myGameEngine;
    }

    private double parseDouble (String input) {
        // TODO include errors
        return Double.parseDouble(input);
    }

}

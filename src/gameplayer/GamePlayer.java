package gameplayer;

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
    
    private static final double PREFWIDTH = 1000;
    private static final double PREFHEIGHT = 600;

    private Stage myStage = new Stage();
    private BorderPane myPane = new BorderPane();
    private Pane levelPane = new Pane();
    private Scene myScene = new Scene(myPane);
    private IGameEngine myGameEngine;
    
    public GamePlayer (IGame game) {
        initializeGameEngine(game);
        stylePane();
    }

    private void stylePane () {
        myPane.setPrefSize(PREFWIDTH, PREFHEIGHT);
        myStage.setScene(myScene);
        myStage.show();
    }

    private void initializeGameEngine (IGame game) {
        IOInterpeter IO = new IOInterpeter(myScene, levelPane);
        myGameEngine = new GameEngine(game, myPane, levelPane, IO);
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

}

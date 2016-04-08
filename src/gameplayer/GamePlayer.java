package gameplayer;

import engine.IGame;
import engine.IOInterpeter;
import javafx.scene.Scene;
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

    private Stage myStage = new Stage();
    private Pane myPane = new Pane();
    private Scene myScene = new Scene(myPane);
    private IGameEngine myGameEngine;
    
    public GamePlayer (IGame game) {
        initializeGameEngine(game);
        stylePane();
        myStage.setScene(myScene);
        myStage.show();
    }

    private void stylePane () {
        myPane.setPrefSize(600, 600);
    }

    private void initializeGameEngine (IGame game) {
        IOInterpeter IO = new IOInterpeter(myScene, myPane);
        myGameEngine = new GameEngine(game, myPane, IO);
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

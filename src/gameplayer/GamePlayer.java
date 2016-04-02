package gameplayer;

import engine.IGame;
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
    private IGameEngine myGameEngine;

    public GamePlayer (IGame game) {
        initializeGameEngine(game);
        myStage.setScene(new Scene(myPane));
        myStage.show();
    }

    private void initializeGameEngine (IGame game) {
        myGameEngine = new GameEngine(game, myPane);
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

package testing;

import engine.Attribute;
import engine.AttributeType;
import engine.Game;
import engine.IGame;
import engine.ILevel;
import gameplayer.GamePlayer;
import javafx.application.Application;
import javafx.stage.Stage;

public class DemoLauncher extends Application {
    
    private IGame myGame;

    @Override
    public void start (Stage primaryStage) throws Exception {
        makeGame();
        GamePlayer gp = new GamePlayer(myGame);
    }
    
    private void makeGame () {
        IGame game = new Game();
        createGlobalAtts(game);
        myGame = game;
    }

    private void createGlobalAtts (IGame game) {
        game.getAttributeManager().getAttributes().add(new Attribute(new AttributeType("Test")));
    }

    public static void main (String[] args) {
        new DemoLauncher();
        launch(args);
    }

}

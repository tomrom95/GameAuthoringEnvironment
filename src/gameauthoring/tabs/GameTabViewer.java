package gameauthoring.tabs;

import engine.IGame;
import engine.IGameInformation;
import gameauthoring.gameinformation.GameInfoController;
import javafx.scene.Node;


/**
 * GameTabViewer serves to display Text/Image EntryView to authors so that they can fill in the game
 * information such as name of the game, author of the game, and an image file for splash screen.
 * 
 * TODO: Multiple Language for label
 * 
 * @author Jin An
 *
 */
public class GameTabViewer implements ITabViewer {

    private GameInfoController myController;
    private IGame myGame;

    public GameTabViewer () {
        init();
    }

    public GameTabViewer (IGame iGame) {
        myGame = iGame;
        init();
    }

    @Override
    public void init () {
        myController = new GameInfoController(myGame);
    }

    @Override
    public Node draw () {
        return myController.getGameInfoView().draw();
    }

    public void setGameInformation () {
        myController.setGameInformation();
    }
}

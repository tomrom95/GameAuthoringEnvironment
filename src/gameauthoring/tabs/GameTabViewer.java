package gameauthoring.tabs;

import engine.IGame;
import gameauthoring.gameinformation.GameInfoController;
import javafx.scene.Node;


/**
 * GameTabViewer serves to display Text/Image EntryView to authors so that they can fill in the game
 * information such as name of the game, author of the game, date created, and an image file for
 * splash screen. It instantiates the controller class.
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

    @Override
    public void rescale (double width, double height) {
        // TODO Auto-generated method stub
        
    }
}

package gameauthoring.gameinformation;

import engine.IGame;
import gameauthoring.creation.entryviews.FormDataManager;
import gameauthoring.creation.entryviews.IFormDataManager;


/**
 * Controller class for Game Information. When save button is pressed in the AuthoringView,
 * setGameInformation() will be called to connect with backend. It contains the gameInfoView which
 * renders the view
 * 
 * @author Jin An
 *
 */
public class GameInfoController {

    private GameInfoView myView;
    private IGame myGame;

    public GameInfoController (IGame game) {
        myView = new GameInfoView();
        myGame = game;
        initialize();
    }

    private void initialize () {
       myView.getMyAuthor().bindData(myGame.getGameInformation().getAuthorProperty());
       myView.getMyDateCreated().bindData(myGame.getGameInformation().getDateCreatedProperty());
       myView.getMyName().bindData(myGame.getGameInformation().getNameProperty());
       myView.getMySplashScreen().bindData(myGame.getGameInformation().getSplashScreenURLProperty());
    }

    public GameInfoView getGameInfoView () {
        return myView;
    }

}

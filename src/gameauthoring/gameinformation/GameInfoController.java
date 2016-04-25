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
    private IFormDataManager myFormData;

    public GameInfoController (IGame game) {
        myView = new GameInfoView();
        myGame = game;
        myFormData = myView.getData();
        initialize();
    }

    private void initialize () {
        myFormData.getValueProperty(myView.getMyAuthorKey())
                .bindBidirectional(myGame.getGameInformation().getAuthorProperty());
        myFormData.getValueProperty(myView.getMyDateCreatedKey())
                .bindBidirectional(myGame.getGameInformation().getDateCreatedProperty());
        myFormData.getValueProperty(myView.getMyNameKey())
                .bindBidirectional(myGame.getGameInformation().getNameProperty());
        myFormData.getValueProperty(myView.getMySplashScreenKey())
                .bindBidirectional(myGame.getGameInformation().getSplashScreenURLProperty());
    }

    public GameInfoView getGameInfoView () {
        return myView;
    }

}

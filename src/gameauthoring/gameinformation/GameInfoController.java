package gameauthoring.gameinformation;

import engine.IGame;
import gameauthoring.creation.entryviews.IFormDataManager;


public class GameInfoController {

    private GameInfoView myView;
    private IGame myGame;
    private IFormDataManager myFormData;

    public GameInfoController (IGame game) {
        myView = new GameInfoView();
        myGame = game;
        myFormData = myView.getData();
    }

    public void setGameInformation () {
        String author = myFormData.getValueProperty(myView.getMyAuthorKey()).get();
        String dateCreated = myFormData.getValueProperty(myView.getMyDateCreatedKey()).get();
        String name = myFormData.getValueProperty(myView.getMyNameKey()).get();
        String url = myFormData.getValueProperty(myView.getMySplashScreenKey()).get();

        myGame.getGameInformation().setAuthor(author);
        myGame.getGameInformation().setDateCreated(dateCreated);
        myGame.getGameInformation().setName(name);
        myGame.getGameInformation().setSplashScreen(url);
    }

    public GameInfoView getGameInfoView () {
        return myView;
    }

}

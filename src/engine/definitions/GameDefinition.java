package engine.definitions;

import engine.Game;
import engine.profile.IProfile;


/**
 * Engine Game definition class
 * Will only be a place holder for an object that is already constructed but then placed into the
 * definitions
 *
 * @author jonathanim
 *
 */
public class GameDefinition implements IDefinition {

    private Game myGame;
    private IProfile myProfile;

    public Game create () {
        return myGame;
    }

    public Game getMyGame () {
        return myGame;
    }

    public void setMyGame (Game game) {
        this.myGame = game;
    }

    @Override
    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;

    }

}

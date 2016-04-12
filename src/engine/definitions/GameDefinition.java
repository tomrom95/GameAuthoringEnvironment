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
public class GameDefinition {

    private Game myGame;

    public Game create () {
        return myGame;
    }

    public Game getMyGame () {
        return myGame;
    }

    public void setMyGame (Game game) {
        this.myGame = game;
    }

}

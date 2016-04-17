package engine.definitions;

import engine.IGame;


/**
 * Engine Game definition class
 * Will only be a place holder for an object that is already constructed but then placed into the
 * definitions
 * TODO is this class ever used? I can't see when the authoring would ever be creating a game via a
 * definition.
 *
 * @author jonathanim
 *
 */
public class GameDefinition {

    private IGame myGame;

    public IGame create () {
        return myGame;
    }

    public IGame getMyGame () {
        return myGame;
    }

    public void setMyGame (IGame game) {
        myGame = game;
    }

}

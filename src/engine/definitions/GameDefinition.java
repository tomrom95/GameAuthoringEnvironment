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
public class GameDefinition  {

    private Game myGame;

    public Game create () {
        return myGame;
    }

    public Game getMyGame () {
        return myGame;
    }

<<<<<<< HEAD
    public void setMyGame (Game myGame) {
        this.myGame = myGame;
    }

    
    public ProfileDefinition getProfileDefinition () {
        // TODO Auto-generated method stub
        return null;
    }

    
    public void setProfileDefinition (ProfileDefinition profileDef) {
        // TODO Auto-generated method stub
        
=======
    public void setMyGame (Game game) {
        this.myGame = game;
>>>>>>> 5c956e7a2a513d379853c626b1bd2924f5d9bd91
    }

}

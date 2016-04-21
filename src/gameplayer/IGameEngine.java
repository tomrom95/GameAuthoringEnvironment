package gameplayer;

import engine.IGame;

/**
 * This interface represents a game engine that can be used to run a game. It defines the necessary
 * external ways to affect the game loop.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGameEngine {

    /**
     * play the game loop
     */
    void play ();

    /**
     * pause the game loop
     */
    void pause ();
    
    /**
     * 
     * @return the game the engine is running
     */
    IGame getGame ();

}

package engine;

import java.util.List;


/**
 * Watches for user interaction with the game screen and converts to the proper effects to be passed
 * to the game engine
 * 
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 */

public interface IOInterpeter {

    List<IEffect> getQueuedEffects ();

}

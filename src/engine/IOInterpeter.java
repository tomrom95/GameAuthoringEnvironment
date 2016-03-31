package engine;

import java.util.List;


/**
 * Watches for user interaction with the game screen and converts to the proper effects to be passed to the game engine
 */

public interface IOInterpeter {

    List<IEffect> getQueuedEffects ();

}

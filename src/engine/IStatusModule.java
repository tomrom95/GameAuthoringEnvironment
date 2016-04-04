package engine;

import modules.IModule;

/**
 * This interface represents a module that handles the status of a sprite
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IStatusModule extends IModule {

    /**
     * @return whether this sprite should be removed from the game
     */
    boolean shouldBeRemoved ();
}

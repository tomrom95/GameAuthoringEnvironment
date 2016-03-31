package engine;

import util.TimeDuration;


/**
 * This interface imposes the behavior of being updateable, specifically in a game loop with a
 * defined frame time
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface Updateable {

    /**
     * tells this object to update, informing it also of how much game time has passed since the
     * last call.
     *
     * @param duration since the last frame
     */
    void update (TimeDuration duration);

}

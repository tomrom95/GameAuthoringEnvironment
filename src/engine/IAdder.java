package engine;

/**
 * Stores queue of Sprites to be added on next game cycle
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IAdder extends Updateable {

    /**
     * @param sprite to be added in the next game cycle
     */
    void add (ISprite sprite);

}

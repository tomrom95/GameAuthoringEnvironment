package engine;

/**
 * This interface represents a game launcher, or the class the manages the overall display of a game
 * environment
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGamePlayer {

    /**
     * @param game to be launched and played
     */
    void launchGame (IGame game);

}

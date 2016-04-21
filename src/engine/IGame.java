package engine;

import engine.rendering.IGameGridConfig;

/**
 * This interface defines the functionality for a Game, specifically adding editing functionality to
 * an IGamePlayable
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 * @author Jin An
 *
 */
public interface IGame extends IGamePlayable {

    /**
     * @return the ILevelManager for this Game
     */
    ILevelManager getLevelManager ();

    /**
     * @return the condition manager for game-wide global conditions
     */
    IConditionManager getConditionManager ();

    /**
     * @return the authoring data for this game
     */
    AuthorshipData getAuthorshipData ();

    /**
     * @return the attribute manager for game-wide global attributes
     */
    IAttributeManager getAttributeManager ();
    
    /**
     * @return the placeableManager object that keeps track of placeable terrains
     */
    IPlaceableManager getPlaceableManager ();
    /**
     * @return the object that handles aggregation of obstruction flags into 
     * bitmaps across the game state
     */
    IObstructionManager getObstructionManager ();
    
    /**
     * @return The object that stores information regarding
     * the size of virtual pixel grid and potentially in the future
     * an externally updated scaling factor to support resizing
     */
    IGameGridConfig getGameGridConfig();
}

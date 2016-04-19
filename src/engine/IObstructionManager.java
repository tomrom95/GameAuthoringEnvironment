package engine;

import engine.modules.IModule;
import util.BitMap;

/**
 * This object will interrogate the sprite lists and their status module in order
 * to determine what the current 'passable' and 'impassable' terrains are in the 
 * game state
 * @author jonathanim
 *
 */
public interface IObstructionManager extends Updateable {

    /**
     * Will calculate the aggregate bit map of obstructed locations using the 
     * bounds objects as stored by each sprite
     * @return {@code return} the bit map of the obstructed locations
     */
    BitMap getObstructionMap ();
}

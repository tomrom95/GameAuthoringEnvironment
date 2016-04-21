package engine;

import util.BitMap;


/**
 * This serves to interrogate the sprite lists and their status module to determine which terrains
 * are placeable (defenders) in the game state.
 * 
 * @author Jin An
 *
 */
public interface IPlaceableManager extends Updateable {

    BitMap getPlaceableArea ();
}

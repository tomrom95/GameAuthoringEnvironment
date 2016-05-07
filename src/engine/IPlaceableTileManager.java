package engine;

import util.BitMap;


/**
 * This serves to manage the placeableTile area using BitMap object which contains 2D array of
 * booelans
 *
 * @author Jin An
 *
 */
public interface IPlaceableTileManager {
    BitMap getPlaceableMap ();
}

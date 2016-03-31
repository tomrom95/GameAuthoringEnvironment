package engine;

import util.Coordinate;

/**
 * Stores queue of Sprites to be added on next game cycle
 * 
 */
public interface IAdder {

    void add (ISprite sprite, Coordinate coordinate);

}

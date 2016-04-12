package engine.modules;

import engine.sprite.ISprite;


/**
 * This interface provides the method 
 * @author Dhrumil
 *
 */
public interface IWave {

    /**
     * @return the next Sprite to be spawned
     */
    ISprite getNextSprite ();
}

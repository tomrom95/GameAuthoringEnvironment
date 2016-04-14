package engine.modules;

import engine.sprite.ISprite;


/**
 * This interface provides the methods needed to spawn sprites based on a wave, or time duration step
 *
 */
public interface IWave {

    /**
     * @return the next Sprite to be spawned
     */
    ISprite getNextSprite ();
    
    boolean hasNext ();
}

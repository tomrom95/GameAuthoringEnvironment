package engine.modules;

import engine.sprite.ISprite;


public interface IWave {

    /**
     * @return the next Sprite to be spawned
     */
    ISprite getNextSprite ();
}

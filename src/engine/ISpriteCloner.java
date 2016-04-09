package engine;

import engine.sprite.ISprite;

public interface ISpriteCloner {

    /**
     * @param sprite
     * @return a cloned version of sprite (new reference)
     */
    ISprite clone (ISprite sprite);
}

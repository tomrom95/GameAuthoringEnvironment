package engine;

public interface ISpriteCloner {

    /**
     * @param sprite
     * @return a cloned version of sprite (new reference)
     */
    ISprite clone (ISprite sprite);
}

package engine.modules;

import engine.sprite.ISprite;

/**
 * This interface manages the notion of cloning sprites to 
 * @author Dhrumil
 *
 */

public interface ISpriteDefinition {

    //TODO: Cloning sprite to produce missles 
    
    /**
     * 
     * @return
     */
    public ISprite create ();

}

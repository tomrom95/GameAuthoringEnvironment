package gameauthoring.creation.subforms.fire;

import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;

/**
 * 
 * 
 * @author Joe Lilien
 *
 */
public interface RemovableSpriteSFC extends ISubFormControllerSprite {
    

    void removeModule (SpriteDefinition item);
    
}

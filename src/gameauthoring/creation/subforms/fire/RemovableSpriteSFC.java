package gameauthoring.creation.subforms.fire;

import engine.definitions.FirerDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;


/**
 * 
 * 
 * @author Joe Lilien
 *
 */
public abstract class RemovableSpriteSFC implements ISubFormControllerSprite {

    private SpriteDefinition mySpriteDefinition;

    public void removeModule (FirerDefinition myFireDef) {
        if (mySpriteDefinition != null) {
            if (mySpriteDefinition.getModuleDefinitions().contains(myFireDef)) {
                mySpriteDefinition.remove(myFireDef);
            }
        }
    }
    
    public abstract FirerDefinition getFirerDefinition();

    protected void setMySpriteDefinition (SpriteDefinition mySpriteDefinition) {
        this.mySpriteDefinition = mySpriteDefinition;
    }

}

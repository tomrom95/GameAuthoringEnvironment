package gameauthoring.creation.subforms;

import java.util.List;
import engine.definitions.concrete.SpriteDefinition;


/**
 * Allows user to select sprites to associate with groups
 * 
 * @author Joe Lilien, Jeremy Schreck
 *
 */
public interface ISelectSpriteSFV  extends ISubFormView{

    List<SpriteDefinition> getChosen ();
    
    void setChosen (List<SpriteDefinition> chosenSprites);

}

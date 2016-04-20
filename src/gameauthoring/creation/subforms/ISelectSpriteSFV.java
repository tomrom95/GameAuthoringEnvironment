package gameauthoring.creation.subforms;

import java.util.List;
import engine.definitions.SpriteDefinition;


/**
 * Allows user to select sprites to associate with groups
 * 
 * @author Joe Lilien
 *
 */
public interface ISelectSpriteSFV {

    List<SpriteDefinition> getChosen ();

}

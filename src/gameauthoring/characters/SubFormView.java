package gameauthoring.characters;

import engine.ISprite;
import gameauthoring.Glyph;

/**
 * 
 * @author JoeLilien
 *
 */
public interface SubFormView extends Glyph{
    
    FormData getData();
    
    void populateWithData(ISprite s);
    
    SubFormController getSubFormController();
    
    void setSubFormController(SubFormController controller);

    
}

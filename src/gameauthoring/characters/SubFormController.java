package gameauthoring.characters;

import engine.ISprite;

/**
 * 
 * @author JoeLilien
 *
 */

public interface SubFormController {

    // Need to make this more generic
    // Probably take in full model and general Game object eventually (common identifier for sprite,
    // module, attribute, etc.)
    
    void updateGameModel (ISprite sprite);

    void populateViewsWithData ();
}

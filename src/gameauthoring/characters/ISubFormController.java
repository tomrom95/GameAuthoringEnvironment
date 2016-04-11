package gameauthoring.characters;

import engine.definitions.IDefinition;

/**
 * 
 * @author JoeLilien, Jeremy Schreck
 *
 */

public interface ISubFormController<T extends IDefinition> {

    // Has a subFormView
    // Need to make this more generic
    // Probably take in full model and general Game object eventually (common identifier for sprite,
    // module, attribute, etc.)
    
    void updateItem (T item);

    void populateViewsWithData (T item);
    
    ISubFormView getSubFormView();

}

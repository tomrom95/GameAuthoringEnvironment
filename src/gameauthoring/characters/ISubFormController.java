package gameauthoring.characters;


/**
 * 
 * @author JoeLilien
 *
 */

public interface ISubFormController<T> {

    // Has a subFormView
    // Need to make this more generic
    // Probably take in full model and general Game object eventually (common identifier for sprite,
    // module, attribute, etc.)
    
    void updateGameModel (T item);

    void populateViewsWithData ();
}

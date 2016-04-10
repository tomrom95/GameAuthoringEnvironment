package gameauthoring.characters;

/**
 * 
 * @author JoeLilien, Jeremy Schreck
 *
 */

public interface ISubFormController<T> {

    // Has a subFormView
    // Need to make this more generic
    // Probably take in full model and general Game object eventually (common identifier for sprite,
    // module, attribute, etc.)
    
    void updateGameModel (T item);

    void populateViewsWithData (T item);
    
    ISubFormView getSubFormView();

}

package gameauthoring.creation.subforms;

import engine.definitions.IDefinition;


/**
 * This is a generic interface for any controller of a subform
 * 
 * Note: The subform will be passed the item currently being edited,
 * which it should update based on user input and fill in UI elements based
 * on item data
 * 
 * @author JoeLilien, Jeremy Schreck
 *
 */

public interface ISubFormController<T extends IDefinition> {

    /**
     * Update the given item
     * 
     * @param item The item to update
     */
    void updateItem (T item);

    /**
     * Fill in the UI based on the given item
     * 
     * @param item The item to display
     */
    void populateViewsWithData (T item);

    /**
     * Get the sfc's associated subformview
     * 
     * @return The subformview
     */
    ISubFormView getSubFormView ();

}

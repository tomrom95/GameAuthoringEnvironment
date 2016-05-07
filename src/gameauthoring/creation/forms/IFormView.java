package gameauthoring.creation.forms;

import gameauthoring.util.Glyph;


/**
 * This in an interface for view that displays a form to create new objects
 * in the Creation Views. This would be general for
 * creating sprites, attributes, or interactions
 *
 * @author Tommy
 * @author Jin An
 * @author Jeremy Schreck
 * @author Joe Lilien
 *
 */
public interface IFormView extends Glyph {

    /**
     * Tells the view what method it should call upon save
     *
     * @param action The method to call
     */
    void setSaveAction (Runnable action);

    /**
     * Tells the view what method it should call upon delete
     *
     * @param action The method to call
     */
    void setDeleteAction (Runnable action);

    /**
     * Tells the view what method it should call when user clicks new
     *
     * @param action The method to call
     */
    void setNewAction (Runnable action);
    
    /**
     * Display or hide the form to create an item
     */
    void showOrHideForm (boolean shouldShowForm);


}

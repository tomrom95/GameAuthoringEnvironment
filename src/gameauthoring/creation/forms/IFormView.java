package gameauthoring.creation.forms;

import java.util.List;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.Glyph;


/**
 * View wrapper for the form to create new objects
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
     * Returns the list of subformviews in the form view
     * 
     * @return The list of subformviews
     */
    List<ISubFormView> getSubFormViews ();
    
    /**
     * Sets currently visible views to those associated with currently selected item
     * 
     * @param subFormViews
     */
    void setViews(List<ISubFormView> subFormViews);
    
    /**
     * Display the form to create an item
     */
    void showForm();
    
    /**
     * Hide the form to create an item
     */
    void hideForm();
    
}

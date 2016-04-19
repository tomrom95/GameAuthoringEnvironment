package gameauthoring.creation.forms;

import java.util.List;
import java.util.function.Consumer;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.Glyph;


/**
 * View wrapper for the form to create new objects
 * in the Creation Views. This would be general for
 * creating sprites, attributes, or interactions
 * 
 * 
 * Note: the Consumer methods can really me a method that has no return paramters
 * or arguments, but not sure if this exists
 * 
 * @author Tommy
 * @author Jin An
 * @author Jeremy Schreck
 * @author Joe Lilien
 *
 */
public interface IFormView extends Glyph {
    /**
     * Has SpriteEditorController
     * Has save button (e -> stc.editSprite(List<SubFormView>))
     * Has delete button (e -> stc.deleteSprite())
     * Has List<SubFormView>
     */

    /**
     * Tells the view what method it should call upon save
     * 
     * @param action The method to call
     */
    void setSaveAction (Consumer<?> action);

    /**
     * Tells the view what method it should call upon delete
     * 
     * @param action The method to call
     */
    void setDeleteAction (Consumer<?> action);

    /**
     * Tells the view what method it should call when user clicks new
     * 
     * @param action The method to call
     */
    void setNewAction (Consumer<?> action);

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
    
}

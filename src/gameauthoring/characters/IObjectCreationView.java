package gameauthoring.characters;

import java.util.List;
import java.util.function.Consumer;
import gameauthoring.Glyph;


/**
 * Interface for a view object that lays out an IFormView and an IObjectListView
 * 
 * Note: setters might not be necessary (they will probably be passed in to constructor
 * instead), but for now I include them to specify what an IObjectCreationView
 * will be structured
 * 
 * @author Jeremy Schreck
 *
 */
public interface IObjectCreationView extends Glyph {
    
    /**
     * Tell the view which method it should call to create a "new" item
     * 
     * @param action The action to take when the user decides to create a new ite,
     */
    void setNewAction(Consumer<?> action);
    
    /**
     * Tell the view which method it should call to edit a previously created item
     * 
     * @param action The action to take when the user decides to edit a different item
     */
    void setEditAction(Consumer<E> action);

    /**
     * Set the title of the view to make it clear which types of objects are being created
     * 
     * @param name The name of the category of objects created in this creation view
     */
    void setTitle (String name);

    /**
     * Sets the list of subFormViews
     * 
     * - this way, we can add classes that implement IObjectCreationView and
     * lay out their subforms in different ways
     * - the only requirement is that they have a list of ISubFormViews, so that
     * we can generate those reflectively
     * 
     * @param sumFormViews The list of subFormViews
     */
    void setSubFormViews (List<ISubFormView> subFormViews);

    // Note: don't need the rest of these, unless we want to be able to generate
    // those reflectively too

    /**
     * Get the IObjectListView, which is the view containing the list of objects
     * 
     * 
     * @return The IObjectListView object
     */
    IObjectListView getObjectListView ();

    /**
     * Get the IFormView, which is the view containing the form to create a new object
     * 
     * 
     * @return The IFormView object
     */
    IFormView getFormView ();

    /**
     * Set the IObjectListView, which is the view containing the list of objects
     * 
     * 
     * @param objectListView The IObjectListView object
     */
    void setObjectListView (IObjectListView objectListView);

    /**
     * Set the IFormView, which is the view containing the form to create a new object
     * 
     * 
     * @param formView The IFormView object
     */
    void setFormView (IFormView formView);

    // ICreationController<?> getCreationController (); controller has view instead
}

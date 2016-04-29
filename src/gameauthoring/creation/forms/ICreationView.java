package gameauthoring.creation.forms;

import java.util.List;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;


/**
 * Interface for a view object that lays out an IFormView and an IObjectListView
 * 
 * @author Jeremy Schreck
 *
 */

public interface ICreationView<E> extends Glyph {

    /**
     * Tell the view which method it should call to create a "new" item
     * 
     * @param action The action to take when the user decides to create a new item
     */
    void setNewAction (Runnable action);

    /**
     * Tell the view which method it should call to edit a previously created item
     * 
     * @param action The action to take when the user decides to edit a different item
     */
    void setEditAction (Runnable action);

    /**
     * Get the IObjectListView, which is the view containing the list of objects
     * 
     * 
     * @return The IObjectListView object
     */
    ICreationListView<E> getCreationListView ();

    /**
     * Get the IFormView, which is the view containing the form to create a new object
     * 
     * 
     * @return The IFormView object
     */
    IFormView getFormView ();

    /**
     * Get the observable list of items stored in the list view
     * 
     * @param items The observable list of items
     * 
     */
    ObservableList<E> getItems ();

    /**
     * Get the currently selected item
     * 
     * @return The item
     */
    E getCurrentItem ();

    /**
     * Init method to pass subformviews
     * 
     * @param subFormViews The subformviews of which the formview should consist of
     */
    void init (List<ISubFormView> subFormViews);
}

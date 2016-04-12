package gameauthoring.characters;

import java.util.List;
import java.util.function.Consumer;
import engine.definitions.IDefinition;
import gameauthoring.Glyph;
import javafx.collections.ObservableList;


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

public interface IObjectCreationView<E> extends Glyph {

    /**
     * Tell the view which method it should call to create a "new" item
     * 
     * @param action The action to take when the user decides to create a new ite,
     */
    void setNewAction (Consumer<?> action);

    /**
     * Tell the view which method it should call to edit a previously created item
     * 
     * @param action The action to take when the user decides to edit a different item
     */
    void setEditAction (Consumer<E> action);


    /**
     * Get the IObjectListView, which is the view containing the list of objects
     * 
     * 
     * @return The IObjectListView object
     */
    IObjectListView<E> getObjectListView ();

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

    E getCurrentItem ();
}

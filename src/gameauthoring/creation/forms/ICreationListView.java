package gameauthoring.creation.forms;

import java.util.function.Consumer;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;


/**
 * This is an interface for a view class that displays a list of objects
 * 
 * @author Jeremy Schreck
 *
 */
public interface ICreationListView<E> extends Glyph {

    /**
     * Tell the view which method it should call to edit a previously created item
     * 
     * @param action The action to take when the user decides to edit a different item
     */
    void setEditAction (Consumer<E> action);

    /**
     * Get the items in the list
     * 
     * @return The list of items
     */
    ObservableList<E> getMyItems ();

    /**
     * Set the items in the list
     * 
     * @param items the list of items
     */
    void setMyItems (ObservableList<E> items);

    /**
     * Returns the currently selected item in the list view
     * 
     * @return The item
     */
    E getSelectedItem ();

    /**
     * Selects the given item
     * 
     * @param item The item to be selected
     */
    void setSelectedItem (E item);
    
    /**
     * Re-render the items in the listview
     */
    void refreshItems();

}

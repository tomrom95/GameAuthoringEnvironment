package gameauthoring;

import javafx.collections.ObservableList;


/**
 * This interface defines a wrapper for an object that holds lists of items
 * 
 * @author Jeremy Schreck
 *
 */
public interface IDefinitionCollection<E> {

    /**
     * The title of the collection
     * 
     * @return The title
     */
    String getTitle ();

    /**
     * Get the items stored in this holder
     * 
     * @return An observable list of editable items
     */
    ObservableList<E> getItems ();

    /**
     * Add a item to the list
     * 
     * @param item The item to add
     */
    void addItem (E item);
}

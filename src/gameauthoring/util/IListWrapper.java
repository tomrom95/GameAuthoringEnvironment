package gameauthoring.util;

import javafx.collections.ObservableList;


/**
 * This interface defines a wrapper for a list of items
 * 
 * Note: currently this is a list + a key to use to retrieve the title of the list from a resource
 * file. However, it can be extended by adding more information to the Metadata class
 *
 * @author Jeremy Schreck
 *
 * @param <E> The type of the objects in the list
 */
public interface IListWrapper<E> {

    /**
     * Get the metadata associated with the list of items
     * 
     * @return A metadata object containing relevant metadata about the list
     */
    Metadata getListMetadata ();

    /**
     * Get the items stored in this wrapper object
     *
     * @return An observable list of items of type E
     */
    ObservableList<E> getItems ();

}

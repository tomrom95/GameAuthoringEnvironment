package gameauthoring.creation.forms;

import java.util.function.Consumer;
import gameauthoring.Glyph;
import javafx.collections.ObservableList;

/**
 * This is an interface for a view class that displays a list of objects
 * 
 * @author Jeremy Schreck
 *
 */
public interface IObjectListView<E> extends Glyph {
 
    /**
     * Tell the view which method it should call to edit a previously created item
     * 
     * @param action The action to take when the user decides to edit a different item
     */
    void setEditAction(Consumer<E> action);
    
    /**
     * Get the items in the list
     * 
     * @return The list of items
     */
    ObservableList<E> getMyItems ();

    E getSelectedItem ();
    
    void setSelectedItem (E item);
    
    void setPreviousItem (E item);



}

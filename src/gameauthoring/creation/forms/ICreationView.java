package gameauthoring.creation.forms;

import java.util.List;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;


/**
 * Interface for a view class that displays a list of created items and allows you to create new ones
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
     * Get the currently selected item
     *
     * @return The item
     */
    E getCurrentItem ();
    
    /**
     * Selects the given item
     *
     * @param item The item to be selected
     */
    void setSelectedItem (E item);

}

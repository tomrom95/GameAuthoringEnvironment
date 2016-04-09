package gameauthoring.characters;

import java.util.function.Consumer;
import gameauthoring.Glyph;

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
}

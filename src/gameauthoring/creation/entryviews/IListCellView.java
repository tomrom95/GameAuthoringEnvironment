package gameauthoring.creation.entryviews;

import java.util.function.Consumer;
import gameauthoring.Glyph;

/**
 * Interface for defining how a "List Item" looks
 * during creation. For example, when making sprites,
 * this will show a sprite image, name, and description
 * on the side.
 * @author Tommy, Jeremy Schreck
 *
 */
public interface IListCellView<E> extends Glyph{

    /**
     * Tell the view which method it should call to edit a previously created item
     * 
     * @param action The action to take when the user decides to edit a different item
     */
    void setEditAction(Consumer<E> action);
}

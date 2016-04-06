package gameauthoring.characters;

import java.util.List;
import gameauthoring.SpriteListView;
import javafx.collections.ObservableList;


/**
 * This is a generalized interface for SpriteEditorController.
 * It handles the creation of objects.
 * 
 * @author Jin An
 *
 * @param <ItemType>
 */
public interface CreationController<ItemType> {
    /**
     * Gets observable list from model
     */
    void setItems (ObservableList<ItemType> items);

    void showAndEdit ();

    void createBlank (); // Empty something

    void delete ();

    void edit (List<EntryView> subForms);

    IFormView getForView ();

    SpriteListView getSpriteListView ();

}

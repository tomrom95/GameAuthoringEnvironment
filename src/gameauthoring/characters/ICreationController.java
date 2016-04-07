package gameauthoring.characters;

import java.util.List;
import javafx.collections.ObservableList;


/**
 * This is a generalized interface for SpriteEditorController.
 * It handles the creation of objects.
 * 
 * @author Jin An, Jeremy Schreck
 *
 * @param <ItemType> The type of object to be created and stored -- ex: Sprite, Interaction,
 *        Attribute
 */
public interface ICreationController<ItemType> {

    /**
     * Sets the controller's observable list of items
     * 
     * @param items The observable list of items
     */
    void setItems (ObservableList<ItemType> items);

    /**
     * Sets the controller's IObjectCreationView
     * 
     * @param objectCreationView The IObjectCreationView
     */

    void setObjectCreationView (IObjectCreationView objectCreationView);

    /**
     * Adds a SubFormController to its list of SubFormControllers
     * 
     * @param subFormController The ISubFormController to add
     */
    void addSubFormController (ISubFormController<ItemType> subFormController);

    /*
     * void showAndEdit ();
     * 
     * void createBlank (); // Empty something
     * 
     * void delete ();
     * 
     * void edit (List<EntryView> subForms);
     */

}

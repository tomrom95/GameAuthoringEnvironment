package gameauthoring.characters;

import java.util.List;
import javafx.collections.ObservableList;


/**
 * This is a generalized interface for a controller that handles creation of items
 * 
 * There will be a CreationController for each Character tab, the Attributes tab, and Interactions
 * tab
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

    /**
     * Sets the list of SubFormControllers
     * 
     * @param subFormControllers The list of subFormControllers
     */
    void setSubFormControllers (List<ISubFormController<ItemType>> subFormControllers);

    void setFactory (Factory<? extends ItemType> factory);

    /*
     * void showAndEdit ();
     * 
     * void createBlank (); // Empty something
     * 
     * void delete ();
     * 
     * void save ();
     * 
     * void edit (List<EntryView> subForms); //what is this for? don't think we need it
     */

}

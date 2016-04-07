package gameauthoring.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import engine.ISprite;
import javafx.collections.ObservableList;

/**
 * This class is the high level controller for a creation/list view
 * 
 * TODO: not sure if we need to give it the observable list. We could connect
 * this elsewhere
 * 
 * @author Jeremy Schreck
 *
 * @param <ItemType> The type of object to be created and stored -- ex: Sprite, Interaction,
 *        Attribute
 */
public class CreationController<ItemType> implements ICreationController<ItemType> {
    private ObservableList<ItemType> myItems;
    private IObjectCreationView myView;
    private List<ISubFormController> mySubFormControllers;
    private ItemType myCurrentItem;

    public CreationController () {

    }

    public CreationController (ObservableList<ItemType> items,
                               IObjectCreationView objectCreationView) {
        setItems(items);
        setObjectCreationView(objectCreationView);
        mySubFormControllers = new ArrayList<ISubFormController>();
        init();
    }

    private void init () {
        IFormView formView = getMyObjectCreationView().getFormView();
        formView.setSaveAction(e -> saveItem(e));
        formView.setDeleteAction(e -> deleteItem(e));
    }

    /**
     * Save the item currently being edited in the form
     * 
     */
    private void saveItem (Object item) {
        for (ISubFormController subFormController : getMySubFormControllers()) {
            subFormController.updateGameModel((ISprite)item); //make more generic later
        }
        addItem(item);
    }

    /**
     * Delete the given item
     * 
     * TODO: it gives me an error when I say ItemType item. How do I make this not give
     * me an error? Do I have to change IFormView to setDeleteAction(Consumer<ItemType>)
     * instead of setDeleteAction(Consumer<?>) ?
     * 
     * @param item the item to delete
     */
    private void deleteItem (Object item) {

    }

    /**
     * Add the given item to the list of available items in the model
     * 
     * @param item The item to add
     */
    private void addItem (Object item) {
        // add to model
    }

    // Getters and setters
    private ObservableList<ItemType> getMyItems () {
        return myItems;
    }

    private IObjectCreationView getMyObjectCreationView () {
        return myView;
    }

    @Override
    public void setItems (ObservableList<ItemType> items) {
        this.myItems = items;
    }

    @Override
    public void setObjectCreationView (IObjectCreationView objectCreationView) {
        this.myView = objectCreationView;
    }

    @Override
    public void addSubFormController (ISubFormController subFormController) {
        mySubFormControllers.add(subFormController);
    }

    private List<ISubFormController> getMySubFormControllers () {
        return mySubFormControllers;
    }

}

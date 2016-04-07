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
    private List<ISubFormController<ItemType>> mySubFormControllers;
    private ItemType myCurrentItem;

    public CreationController () {

    }

    public CreationController (ObservableList<ItemType> items,
                               IObjectCreationView objectCreationView) {
        setItems(items);
        setObjectCreationView(objectCreationView);
        mySubFormControllers = new ArrayList<ISubFormController<ItemType>>();
        init();
    }

    private void init () {
        IFormView formView = getMyObjectCreationView().getFormView();
        formView.setSaveAction(e -> saveItem());
        formView.setDeleteAction(e -> deleteItem());
    }

    /**
     * Save the item currently being edited in the form
     * 
     */
    private void saveItem () {
        for (ISubFormController<ItemType> subFormController : getMySubFormControllers()) {
            subFormController.updateGameModel(getMyCurrentItem()); //make more generic later
        }
        addItem(getMyCurrentItem());
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
    private void deleteItem () {

        //model.removeItem(getMyCurrentItem())
    }

    /**
     * Add the given item to the list of available items in the model
     * 
     * @param item The item to add
     */
    private void addItem (Object item) {
        // add to model
        //model.addItem(getMyCurrentItem());

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
    public void addSubFormController (ISubFormController<ItemType> subFormController) {
        mySubFormControllers.add(subFormController);
    }

    @Override
    public void setSubFormControllers (List<ISubFormController<ItemType>> subFormControllers) {
        this.mySubFormControllers = subFormControllers;
        
    }
    
    private List<ISubFormController<ItemType>> getMySubFormControllers () {
        return mySubFormControllers;
    }
    
    private ItemType getMyCurrentItem() {
        return myCurrentItem;
    }

}

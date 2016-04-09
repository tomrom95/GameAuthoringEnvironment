package gameauthoring.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
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
    private Factory<? extends ItemType> myFactory;

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
        
        IObjectCreationView creationView = getMyObjectCreationView();
        //creationView.setEditAction(e -> showAndEdit(e));
        creationView.setNewAction(e -> createBlankItem());
        
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
     * 
     * Instead: delete the item currently being edited in the form
     */
    private void deleteItem () {
        getMyItems().remove(getMyCurrentItem());
    }

    
    private void createBlankItem() {
        //create new itemType() using factory class
        //show and edit itemType
        ItemType item = myFactory.create();
        showAndEdit(item);
    }
    
    private void showAndEdit(ItemType item) {
        setMyCurrentItem(item);
        for (ISubFormController<ItemType> subFormController : getMySubFormControllers()) {
            subFormController.populateViewsWithData(item);
        }
        
    }
    
    /**
     * Add the given item to the list of available items in the model
     * 
     * @param item The item to add
     */
    private void addItem (ItemType item) {
        // add to model
        //model.addItem(getMyCurrentItem());

        getMyItems().add(item);
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
    
    private void setMyCurrentItem(ItemType item) {
        this.myCurrentItem = item;
    }

    @Override
    public void setFactory (Factory<? extends ItemType> factory) {
        this.myFactory = factory;
    }
}

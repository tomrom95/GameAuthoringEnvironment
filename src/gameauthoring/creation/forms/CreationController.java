package gameauthoring.creation.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import engine.definitions.IDefinition;
import engine.definitions.SpriteDefinition;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.collections.ObservableList;


/**
 * This class is the high level controller for a creation/list view
 * 
 * TODO: not sure if we need to give it the observable list. We could connect
 * this elsewhere
 * 
 * @author Jeremy Schreck
 * @author Joe Lilien
 *
 * @param <T> The type of object to be created and stored -- ex: Sprite, Interaction,
 *        Attribute
 */
public abstract class CreationController<T extends IProfilable> {
    private IObjectCreationView<T> myView;
    private List<? extends ISubFormController<T>> mySubFormControllers;
   // private T myCurrentItem;

    public CreationController (List<? extends ISubFormController<T>> subFormControllers) {
        mySubFormControllers = subFormControllers;
        List<ISubFormView> subFormViews = getSubFormViews(mySubFormControllers);
        myView = new ObjectCreationView<T>(subFormViews);
        init();
        newItem();
       
    }

    private List<ISubFormView> getSubFormViews (List<? extends ISubFormController<T>> subFormControllers) {
        List<ISubFormView> subFormViews = new ArrayList<ISubFormView>();

        for (ISubFormController<T> subFormController : subFormControllers) {
            subFormViews.add(subFormController.getSubFormView());
        }
        return subFormViews;
    }

    /**
     * Set up event handler connections
     */
    private void init () {
        IFormView formView = getMyObjectCreationView().getFormView();
        formView.setSaveAction(e -> saveItem());
        formView.setDeleteAction(e -> deleteItem());
        formView.setNewAction(e-> newItem());

        IObjectCreationView<T> creationView = getMyObjectCreationView();
        creationView.setEditAction(e -> showAndEdit(e));

    }

    /**
     * Save the item currently being edited in the form
     * 
     */
    private void saveItem () {
        for (ISubFormController<T> subFormController : getMySubFormControllers()) {
            subFormController.updateItem(getMyCurrentItem()); // make more generic later
        }        
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
     *        Instead: delete the item currently being edited in the form
     */
    private void deleteItem () {
        getMyItems().remove(getMyCurrentItem());
    }

    /**
     * Method handler when user clicks "new" object
     * 
     * Note: need blank definitions to have empty strings instead of null so that
     * no front end erros occur
     * 
     * 
     */
    private void newItem () {
        T item = createBlankItem();
        showAndEdit(item);
        addItem(item);
    }

    protected abstract T createBlankItem ();

    /**
     * Method called when user clicks a cell in the list view
     * 
     * @param item The item contained in the cell that was clicked
     */
    private void showAndEdit (T item) {
//        setMyCurrentItem(item);
        System.out.println(item);
        for (ISubFormController<T> subFormController : getMySubFormControllers()) {
            subFormController.populateViewsWithData(item);
        }

    }

    /**
     * Add the given item to the list of available items in the model
     * 
     * @param item The item to add
     */
    private void addItem (T item) {
        getMyItems().add(item);
    }

    // Getters and setters
    private ObservableList<T> getMyItems () {
        return getMyObjectCreationView().getItems();
    }

    public IObjectCreationView<T> getMyObjectCreationView () {
        return myView;
    }

    private List<? extends ISubFormController<T>> getMySubFormControllers () {
        return mySubFormControllers;
    }

    private T getMyCurrentItem () {
        return this.myView.getCurrentItem();
    }

//    private void setMyCurrentItem (T item) {
//        this.myCurrentItem = item;
//    }
}

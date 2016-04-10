package gameauthoring.characters;

import java.util.ArrayList;
import java.util.List;
import engine.definitions.IDefinition;
import engine.definitions.SpriteDefinition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CreationControllerSprite {

    private SpriteDefinition myCurrentItem;
    private IObjectCreationView<SpriteDefinition> myView;
    private List<ISubFormController> mySubFormControllers;

    public CreationControllerSprite (List<ISubFormController> subFormControllers) {
        mySubFormControllers = subFormControllers;

        ObservableList<SpriteDefinition> items = FXCollections.observableArrayList();
        IObjectListView<SpriteDefinition> objectListView =
                new ObjectListView<SpriteDefinition>(items);

        IFormView formView = createFormView(mySubFormControllers);

        myView = new ObjectCreationView<SpriteDefinition>(objectListView, formView);
        connectCreationViewActions(myView);
    }

    private IFormView createFormView (List<? extends ISubFormController> subFormControllers) {
        List<ISubFormView> subFormViews = new ArrayList<ISubFormView>();
        for (ISubFormController subFormController : subFormControllers) {
            ISubFormView subFormView = subFormController.getSubFormView();
            subFormViews.add(subFormView);
        }
        IFormView formView = new FormView(subFormViews);
        connectFormViewActions(formView);
        return formView;
    }

    private void connectFormViewActions (IFormView formView) {
        formView.setSaveAction(e -> saveItem());
        formView.setDeleteAction(e -> deleteItem());
    }

    private void connectCreationViewActions (IObjectCreationView<?> creationView) {
        myView.setEditAction(e -> showAndEdit(e));
        myView.setNewAction(e -> createBlankItem());
    }

    /**
     * Save the item currently being edited in the form
     * 
     */
    private void saveItem () {
        for (ISubFormControllerSprite subFormController : getMySubFormControllers()) {
            subFormController.updateGameModel(getMyCurrentItem()); // make more generic later
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
        getMyObjectCreationView().getObjectListView().removeItem(getMyCurrentItem());
    }

    private void addItem (SpriteDefinition item) {
        getMyObjectCreationView().getObjectListView().addItem(getMyCurrentItem());

    }

    private void createBlankItem () {
        // create new itemType() using factory class
        // show and edit itemType
        SpriteDefinition item = new SpriteDefinition();
        addItem(item);
        showAndEdit(item);
    }

    private void showAndEdit (SpriteDefinition item) {
        setMyCurrentItem(item);
        for (ISubFormController subFormController : getMySubFormControllers()) {
            ISubFormControllerSprite test = (ISubFormControllerSprite) subFormController;
            test.populateViewsWithData(item);
        }

    }

    private List<ISubFormController> getMySubFormControllers () {
        return mySubFormControllers;
    }

    private SpriteDefinition getMyCurrentItem () {
        return myCurrentItem;
    }

    private void setMyCurrentItem (SpriteDefinition item) {
        this.myCurrentItem = item;
    }

    private IObjectCreationView<SpriteDefinition> getMyObjectCreationView () {
        return myView;
    }

}

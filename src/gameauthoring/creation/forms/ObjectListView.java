package gameauthoring.creation.forms;

import java.util.function.Consumer;
import engine.profile.IProfilable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;


/**
 * View class for individual object list in Object Creation Tab.
 * Has "Show and Edit" button for the user to load specific ListCell on the right
 * Has "Create" button to create a FormView and load it on the right
 * 
 * Has SpriteEditorController
 * Has buttons that map cells to CreationController.showAndEdit()
 * Create button (e -> stc.createBlankSprite())
 * 
 * @author Jin An, Jeremy Schreck
 *
 */
public class ObjectListView<E extends IProfilable> implements IObjectListView<E> {

    private ObservableList<E> myItems; // maybe change to SpriteListHolder
    private ListView<E> myListView;

    public ObjectListView (ObservableList<E> items) {
        myItems = items;
        myListView = new ListView<E>();
        myListView.setItems(getMyItems());
        
    }

    @Override
    public Node draw () {
        return myListView;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEditAction (Consumer<E> action) {
        getMyListView().setOnMouseClicked(e -> handleEditCell(action));

    }

    /**
     * Event handler for cell selection. Passes the item in the listview to the
     * given Consumer action
     * 
     * @param action The consumer to which to pass the selected item
     */
    private void handleEditCell (Consumer<E> action) {
        E item = getMyListView().getSelectionModel().getSelectedItem();
        action.accept(item);
    }

    @Override
    public ObservableList<E> getMyItems () {
        return myItems;
    }

    private ListView<E> getMyListView () {
        return myListView;
    }

    @Override
    public E getSelectedItem () {
        return this.myListView.getSelectionModel().getSelectedItem();
    }

    @Override
    public void setSelectedItem (E item) {
        this.myListView.getSelectionModel().select(item);
        
    }

    @Override
    public void setPreviousItem (E item) {
        //this.myListView.getSelectionModel().g
        
    }

}

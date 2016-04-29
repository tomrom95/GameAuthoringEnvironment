package gameauthoring.creation.forms;

import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


/**
 * View class for individual object lists in Object Creation Tabs
 * 
 * @author Jin An, Jeremy Schreck
 *
 */
public class CreationListView<E extends IProfilable> implements ICreationListView<E> {

    private ObservableList<E> myItems;
    private ListView<E> myListView;
    private static final double HEIGHT = 548; // TODO: move to common resource file

    public CreationListView (ObservableList<E> items) {
        myItems = items;
        myListView = new ListView<E>();
        myListView.setItems(getMyItems());
        myListView.setCellFactory(c -> new ProfileCellView<E>());
        myListView.setMinHeight(HEIGHT);
        myListView.setMaxHeight(HEIGHT);
        myListView.setPlaceholder(new Label("Created objects will show up here."));

        // TODO: resource file and maybe constructor arguement later
        myListView.getStyleClass().add("myObjectListView");
    }

    @Override
    public Node draw () {
        return myListView;
    }

    @Override
    public void setEditAction (Runnable action) {
        getMyListView().getSelectionModel().selectedItemProperty()
                .addListener( (observable, oldValue, newValue) -> action.run());

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
    public void setMyItems (ObservableList<E> items) {
        this.myItems = items;
        myListView.setItems(items);
    }

    @Override
    public void refreshItems () {
        ObservableList<E> items = this.myListView.getItems();
        E selectedItem = this.getSelectedItem();
        this.myListView.setItems(null);
        this.myListView.setItems(items);
        this.setSelectedItem(selectedItem);

    }

}

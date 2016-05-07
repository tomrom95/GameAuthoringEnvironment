package gameauthoring.creation.forms;

import java.util.List;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * Object that holds and displays both view items that make up object creation interface
 *
 * @author Joe Lilien, Jeremy Schreck
 *
 */
public class CreationView<E extends IProfilable> implements ICreationView<E> {

    private ICreationListView<E> myCreationListView;
    private IFormView myFormView;
    private GridPane myCreationPane = new GridPane();

    /**
     * Constructor
     *
     * Note: the subformviews in an ObjectCreationView are customizable
     * - this way, we can add classes that implement IObjectCreationView and
     * lay out their subforms in different ways
     * - the only requirement is that they have a list of ISubFormViews, so that
     * we can generate those reflectively
     *
     * Note: part of constructor moved to init method to fix weird dependency
     * issue with some creation controllers needing to share authorshipData with
     * other creation controller's subforms
     *
     * @param items The list of created items
     * @param subFormViews The SubformViews to create the FormView with
     */
    public CreationView (ObservableList<E> items, List<ISubFormView> subFormViews) {
        createListView(items);
        createFormView(items, subFormViews);
        initViewLayout();

    }

    /**
     * Create my CreationListView
     * 
     * @param items The list of created items to show in the view
     */
    private void createListView (ObservableList<E> items) {
        this.myCreationListView = new CreationListView<E>(items);
    }

    /**
     * Create my FormView
     * 
     * @param items The list of created items
     * @param subFormViews The SubformViews to create the FormView with
     */
    private void createFormView (ObservableList<E> items, List<ISubFormView> subFormViews) {
        this.myFormView = new FormView(subFormViews);

        Bindings.isEmpty(items).addListener( (obs, wasEmpty, isNowEmpty) -> {
            getMyFormView().showOrHideForm(wasEmpty);
        });

    }

    /**
     * Setup the view layout
     *
     */
    public void initViewLayout () {
        GridPane.setValignment(getMyCreationListView().draw(), VPos.TOP);

        myCreationPane.add(getMyCreationListView().draw(), 0, 0);
        myCreationPane.add(getMyFormView().draw(), 1, 0);

    }

    // Glyph interface methods

    @Override
    public Node draw () {
        return myCreationPane;
    }

    // ICreationView interface methods

    @Override
    public void setSaveAction (Runnable action) {
        getMyFormView().setSaveAction(action);
    }

    @Override
    public void setDeleteAction (Runnable action) {
        getMyFormView().setDeleteAction(action);
    }

    @Override
    public void setNewAction (Runnable action) {
        getMyFormView().setNewAction(action);
    }

    @Override
    public void setEditAction (Runnable action) {
        getMyCreationListView().setEditAction(action);
    }

    @Override
    public E getCurrentItem () {
        return getMyCreationListView().getSelectedItem();
    }

    @Override
    public void setSelectedItem (E item) {
        getMyCreationListView().setSelectedItem(item);
    }

    // Getters and Setters

    private ICreationListView<E> getMyCreationListView () {
        return myCreationListView;
    }

    private IFormView getMyFormView () {
        return myFormView;
    }

}

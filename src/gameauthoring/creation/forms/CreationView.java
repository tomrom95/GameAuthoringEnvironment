package gameauthoring.creation.forms;

import java.util.List;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    private Button myNewButton = new Button("New");
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
     * @param subFormViews The subformviews to create the FormView with
     */
    public CreationView () {
        ObservableList<E> items = FXCollections.observableArrayList();
        this.myCreationListView = new CreationListView<E>(items);

    }

    /**
     * Initialize view
     */
    public void init (List<ISubFormView> subFormViews) {
        this.myFormView = new FormView(subFormViews);

        myCreationPane.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(myCreationListView.draw(), VPos.TOP);
        
        myCreationPane.add(myCreationListView.draw(), 0, 0);
        myCreationPane.add(myFormView.draw(), 1, 0);
        
        //myCreationPane.getStyleClass().add("myCreationView");

    }
    
    @Override
    public Node draw () {
        return myCreationPane;
    }

    // Getters and Setters

    @Override
    public ICreationListView<E> getCreationListView () {
        return myCreationListView;
    }

    @Override
    public IFormView getFormView () {
        return myFormView;
    }

    @Override
    public void setNewAction (Runnable action) {
        myNewButton.setOnAction(e -> action.run());
    }

    @Override
    public void setEditAction (Runnable action) {
        // set listcell's edit button's setOnAction to call action
        getCreationListView().setEditAction(action);

    }

    @Override
    public ObservableList<E> getItems () {
        return getCreationListView().getMyItems();

    }

    @Override
    public E getCurrentItem () {
        return getCreationListView().getSelectedItem();
    }

}

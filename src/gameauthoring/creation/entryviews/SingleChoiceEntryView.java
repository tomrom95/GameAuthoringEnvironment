package gameauthoring.creation.entryviews;

import java.util.function.Consumer;
import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.NameCellView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


/**
 * Allows for Single choice from list of given choices associated with Label given as constructor
 * Arguement
 * 
 * 
 * @author JoeLilien
 *
 */
public class SingleChoiceEntryView<E extends IProfilable> extends EntryView {
    private GridPane myContainer;
    private ComboBox<E> myChoices;

    public SingleChoiceEntryView (String label, ObservableList<E> observableList, String cssClass) {
        super(label);
        this.myChoices = new ComboBox<E>(observableList);
        myChoices.setCellFactory(c -> new NameCellView<E>());
        myChoices.setButtonCell(new NameCellView<E>());
        init(label, cssClass);

    }

    @Override
    protected void init (String label, String cssClass) {
        this.myContainer = new GridPane();
        myContainer.add(getLabel(), 0, 0);
        myContainer.add(myChoices, 0, 1);
        myContainer.getStyleClass().add(cssClass);
    }

    public void setItems (ObservableList<E> items) {
        myChoices.setItems(items);
    }

    public void setSelected (E item) {
        myChoices.getSelectionModel().select(item);
    }
    
    public void setSelected (int index) {
        myChoices.getSelectionModel().select(0);
    }

    public void clearSelection () {
        myChoices.getSelectionModel().clearSelection();
    }

    public E getSelected () {
        return myChoices.getSelectionModel().getSelectedItem();
    }

    public void addComboIndexListener (Consumer<Integer> action) {
        myChoices.setOnAction(e -> action.accept(myChoices.getSelectionModel().getSelectedIndex()));

    }

    public void addComboItemListener (Consumer<E> action) {
        myChoices.setOnAction(e -> action.accept(myChoices.getSelectionModel().getSelectedItem()));

    }

    @Override
    public Node draw () {
        return myContainer;
    }

    public ObservableList<E> getItems () {
        return myChoices.getItems();
    }

}

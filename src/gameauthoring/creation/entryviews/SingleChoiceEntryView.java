package gameauthoring.creation.entryviews;

import java.util.function.Consumer;
import engine.profile.IProfilable;
import gameauthoring.ProfileCellView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    public SingleChoiceEntryView (String label, ObservableList<E> observableList, double spacing) {
        super(label);
        this.myContainer = new GridPane();
        this.myChoices = new ComboBox<E>(observableList);
        myChoices.setCellFactory(c -> new NameCellView<E>());
        myChoices.setButtonCell(new NameCellView<E>());

        myContainer.add(new Label(getLabel()), 0, 0);
        myContainer.add(myChoices, 0, 1);
    }

    public void setSelected (E item) {
        myChoices.getSelectionModel().select(item);
    }

    public E getSelected () {
        return myChoices.getSelectionModel().getSelectedItem();
    }

    public void addComboListener(Consumer<Integer> action){
        myChoices.setOnAction(e -> action.accept(myChoices.getSelectionModel().getSelectedIndex()));
        
    }
    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node draw () {
        return myContainer;
    }

}

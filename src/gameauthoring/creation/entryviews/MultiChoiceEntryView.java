package gameauthoring.creation.entryviews;

import java.util.List;
import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;


/**
 * Allows Users to select multiple items from a given list in a choice box fashion to assoicate with
 * a given label
 *
 *
 * @author JoeLilien
 *
 */
public class MultiChoiceEntryView<E extends IProfilable> extends EntryView {

    private ListView<E> myChoices;
    private String myListViewStyle = "MultiChoiceView";

    public MultiChoiceEntryView (String label,
                                 ObservableList<E> observableList,
                                 int width,
                                 int height,
                                 String cssClass) {
        super(label, cssClass);
        myChoices = new ListView<E>(observableList);
        getMyFactory().addStyling(myChoices, myListViewStyle);
        myChoices.setMinSize(width, height);
        myChoices.setMaxSize(width, height);
        init();
    }

    @Override
    protected void init () {
        myChoices.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        myChoices.setCellFactory(c -> new ProfileCellView<E>());
        getMyContainer().getChildren().add(myChoices);
    }

    public void setSelected (List<E> items) {
        myChoices.getSelectionModel().clearSelection();
        if (items != null) {
            for (E item : items) {
                myChoices.getSelectionModel().select(item);
            }
        }

    }

    public void clearSelection () {
        myChoices.getSelectionModel().clearSelection();
    }

    public ListView<E> getListView () {
        return myChoices;
    }

    public List<E> getSelected () {
        return myChoices.getSelectionModel().getSelectedItems();
    }

    @Override
    public Node draw () {
        return getMyContainer();
    }

    public void select (E spr) {
        myChoices.getSelectionModel().select(spr);
    }

}

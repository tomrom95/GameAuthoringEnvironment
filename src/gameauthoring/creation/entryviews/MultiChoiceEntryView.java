package gameauthoring.creation.entryviews;

import java.util.List;
import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * Allows Users to select multiple items from a given list in a choice box fashion to assoicate with
 * a given label
 * 
 * NOTE: this implementation will be easier if we are allowed to import a third party library. I
 * will ask professor Duvall about it on Thursday
 * 
 * @author JoeLilien
 *
 */
public class MultiChoiceEntryView<E extends IProfilable> extends EntryView {
    private GridPane myContainer;
    private ListView<E> myChoices;

    public MultiChoiceEntryView (String label, ObservableList<E> observableList, int width, int height, String cssClass) {
        super(label);
        this.myContainer = new GridPane();
        this.myChoices = new ListView<E>(observableList);
        this.myChoices.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.myChoices.setMinSize(width, height);
        this.myChoices.setMaxSize(width, height);
        myChoices.setCellFactory( c -> new ProfileCellView<E>());
        myContainer.add(new Label(label), 0, 0);
        myContainer.add(myChoices,0,1);
        myContainer.getStyleClass().add(cssClass);
    }

    public void setSelected (List<E> items) {
        myChoices.getSelectionModel().clearSelection();
        if(items!=null){
            for (E item : items) {                
                myChoices.getSelectionModel().select(item);
            }
        }
        
    }

    public void clearSelection(){
        myChoices.getSelectionModel().clearSelection();
    }
    public ListView<E> getListView(){
        return myChoices;
    }
    public List<E> getSelected () {
        return myChoices.getSelectionModel().getSelectedItems();
    }

    @Override
    public Node draw () {
        return myContainer;
    }

    public void select (E spr) {
        myChoices.getSelectionModel().select(spr);
    }

}

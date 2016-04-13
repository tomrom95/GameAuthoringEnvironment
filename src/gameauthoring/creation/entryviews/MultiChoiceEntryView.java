package gameauthoring.creation.entryviews;

import java.util.List;
import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
    private String myLabel;
    private HBox myContainer;
    private ListView<E> myChoices;

    public MultiChoiceEntryView (String label, ObservableList<E> observableList, double spacing) {
        super(label);
        this.myContainer = new HBox(spacing);
        this.myChoices = new ListView<E>(observableList);
        this.myChoices.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        myChoices.setCellFactory( c -> new ProfileCellView<E>());
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myChoices);
    }

    public void setSelected (List<E> items) {
        myChoices.getSelectionModel().clearSelection();
        if(items!=null){
            for (E item : items) {                
                myChoices.getSelectionModel().select(item);
                System.out.println(item);
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

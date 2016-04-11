package gameauthoring.creation.entryviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


/**
 * Allows for Single choice from list of given choices associated with Label given as constructor
 * Arguement
 * 
 * 
 * @author JoeLilien
 *
 */
public class SingleChoiceEntryView extends EntryView{
    private String myLabel;
    private HBox myContainer;
    private ChoiceBox<String> myChoices;

    public SingleChoiceEntryView (String label, IFormDataManager data, List<String> choices, double spacing) {
        super(label,data);
        ObservableList<String> obsChoices = FXCollections.observableList(choices);
        this.myContainer = new HBox(spacing);
        this.myChoices = new ChoiceBox<String>(obsChoices);
        myChoices.valueProperty().bindBidirectional(this.getData().getValueProperty());
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myChoices);
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

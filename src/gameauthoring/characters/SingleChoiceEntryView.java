package gameauthoring.characters;

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
public class SingleChoiceEntryView implements EntryView {
    private String myLabel;
    private HBox myContainer;
    private ChoiceBox<String> myChoices;

    public SingleChoiceEntryView (String label, List<Object> choices, double spacing) {
        List<String> stringChoices = generateStringList(choices);
        ObservableList<String> obsChoices = FXCollections.observableList(stringChoices);
        this.myLabel = label;
        this.myContainer = new HBox(spacing);
        this.myChoices = new ChoiceBox<String>(obsChoices);
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myChoices);
    }

    /*
     * NOTE: We need to decide if we want the choice box is driven by a list of objects or a list of
     * strings
     * 
     */
    private List<String> generateStringList (List<Object> choices) {
        List<String> strings = new ArrayList<String>();
        for (Object object : choices) {
            strings.add(object != null ? object.toString() : null);
        }
        return strings;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public FormData getData () {
        String key = myLabel;
        String value = (String) myChoices.getSelectionModel().getSelectedItem();
        return new FormData(key, new ArrayList<String>(Arrays.asList(value)));
    }

    @Override
    public void populateWithData (FormData data) {
        String value = data.getMyValue().get(0);
        this.myChoices.getSelectionModel().select(value);
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}

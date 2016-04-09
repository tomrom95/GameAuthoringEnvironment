package gameauthoring.characters;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;


/**
 * Allows for Text Data Entry Associated with Label given as constructor Arguement
 * 
 * @author JoeLilien
 *
 */
public class TextEntryView extends EntryView {
    private String myLabel;
    private HBox myContainer; // TODO Magic Number and Factory
    private TextField myTextInput = new TextField();
    private boolean isNumberData;
    

    public TextEntryView (String label, IFormDataManager data, double spacing, double width, double height, boolean isNumberData) {
        super(label, data);
        this.isNumberData = isNumberData;
        this.myTextInput.setPrefSize(width, height);
        this.myTextInput.textProperty().bindBidirectional(getData().getValueProperty());
        this.myContainer = new HBox(spacing);
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myTextInput);
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }


    /**
     * Ensures that entry is a double value if it is required
     * 
     * @param key
     * @param value
     * @return
     */
    private FormData getDataWithErrorCheck (String key, String value) {
        try {
            Double.parseDouble(value);
            return new FormData(key, new ArrayList<String>(Arrays.asList(value)));
        }
        catch (IllegalArgumentException e) {
            ErrorMessage err = new ErrorMessage(myLabel + " Value Must Be a Double!");
            err.showError();
            return null;
        }
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}

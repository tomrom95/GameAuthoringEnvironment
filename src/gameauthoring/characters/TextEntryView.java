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
public class TextEntryView implements EntryView {
    private String myLabel;
    private HBox myContainer; // TODO Magic Number and Factory
    private TextField myTextInput = new TextField();
    private boolean isNumberData;

    public TextEntryView (String label, double spacing, double width, double height, boolean isNumberData) {
        this.myLabel = label;
        this.isNumberData = isNumberData;
        this.myTextInput.setPrefSize(width, height);
        this.myContainer = new HBox(spacing);
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myTextInput);
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public FormData getData () {
        String key = myLabel;
        String value = myTextInput.getText();
        if (isNumberData) {
            return getDataWithErrorCheck(key, value);
        }
        return new FormData(key, new ArrayList<String>(Arrays.asList(value)));
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
    public void populateWithData (FormData data) {
        this.myTextInput.setText(data.getMyValue().get(0));
    }  

    @Override
    public Node draw () {
        return myContainer;
    }

}

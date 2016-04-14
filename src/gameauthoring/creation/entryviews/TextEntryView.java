package gameauthoring.creation.entryviews;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


/**
 * Allows for Text Data Entry Associated with Label given as constructor Arguement
 * 
 * @author JoeLilien
 *
 */
public class TextEntryView extends InputEntryView {
    private TextInputControl myTextInput;
    

    public TextEntryView (String label, IFormDataManager data, double width, double height, String cssClass) {
        super(label,data,width,height,cssClass);
        myTextInput = new TextArea();
        super.setInputControl(myTextInput);
        super.init(label,width,height,cssClass);
    }

}

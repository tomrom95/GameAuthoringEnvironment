package gameauthoring.creation.entryviews;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;


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
        super.init(label, cssClass);
    }

}

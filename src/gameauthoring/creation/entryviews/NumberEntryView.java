package gameauthoring.creation.entryviews;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;

/**
 * Allows for Number Data Entry Associated with Label given as constructor Arguement
 * 
 * @author JoeLilien
 *
 */
public class NumberEntryView extends InputEntryView {
    private TextInputControl myTextInput;
    

    public NumberEntryView (String label, IFormDataManager data, double width, double height, String cssClass) {
        super(label,data,width,height,cssClass);
        myTextInput = new NumberTextField();
        super.setInputControl(myTextInput);
        super.init(label,width,height,cssClass);
    }

}
package gameauthoring.creation.entryviews;

import javafx.beans.property.StringProperty;
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

    public TextEntryView (String label, double width, double height, String cssClass) {
        super(label, width, height, cssClass);
        myTextInput = new TextArea();
        super.setInputControl(myTextInput);
        super.init();
    }

    public TextEntryView (String label,
                          double width,
                          double height,
                          String cssClass,
                          String cssLabel) {
        this(label, width, height, cssClass);
        getMyFactory().addStyling(getLabel(), cssLabel);
    }

    public void setData (String data) {
        myTextInput.setText(data);
    }

    public String getData () {
        return myTextInput.getText();
    }

    public void bindData (StringProperty text) {
        myTextInput.textProperty().bindBidirectional(text);
    }

}

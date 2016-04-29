package gameauthoring.creation.entryviews;

import javafx.scene.control.TextInputControl;


public class CharacterEntryView extends InputEntryView {

    private TextInputControl myTextInput;

    public CharacterEntryView (String label,
                               double width,
                               double height,
                               String cssClass) {
        super(label, width, height, cssClass);
        myTextInput = new CharacterTextField();
        super.setInputControl(myTextInput);
        super.init();
    }

    public String getData () {
        return myTextInput.getText();
    }

    public void setData (String data) {
        myTextInput.setText(data);
    }
}

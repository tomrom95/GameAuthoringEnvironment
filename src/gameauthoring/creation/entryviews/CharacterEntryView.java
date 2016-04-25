package gameauthoring.creation.entryviews;

import javafx.scene.control.TextInputControl;

public class CharacterEntryView extends InputEntryView{
    private TextInputControl myTextInput;
    
    public CharacterEntryView (String label,
                               IFormDataManager data,
                               double width,
                               double height,
                               String cssClass) {
        super(label, data, width, height, cssClass);
        myTextInput = new CharacterTextField();
        super.setInputControl(myTextInput);
        super.init(label, cssClass);    }

}

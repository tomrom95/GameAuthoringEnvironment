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
    

    public TextEntryView (String label, double width, double height, String cssClass) {
        super(label,width,height,cssClass);
        myTextInput = new TextArea();
        super.setInputControl(myTextInput);
        super.init(label, cssClass);
    }
    
    public void setData(String data){
        myTextInput.setText(data);
    }
    
    public String getData(){
        return myTextInput.getText();
    }

}

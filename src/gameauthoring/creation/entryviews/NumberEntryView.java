package gameauthoring.creation.entryviews;

import gameauthoring.util.ErrorMessage;
import javafx.scene.control.TextInputControl;

/**
 * Allows for Number Data Entry Associated with Label given as constructor Arguement
 * 
 * @author JoeLilien
 *
 */
public class NumberEntryView extends InputEntryView {
    private TextInputControl myTextInput;
    

    public NumberEntryView (String label, double width, double height, String cssClass) {
        super(label,width,height,cssClass);
        myTextInput = new NumberTextField();
        super.setInputControl(myTextInput);
        super.init(label, cssClass);
    }
    
    public void setData(double data){
        myTextInput.setText(String.valueOf(data));
    }
    
    public double getData(){
        try{
            return Double.parseDouble(myTextInput.getText());
        }
        catch(NumberFormatException e){
            ErrorMessage err = new ErrorMessage("Input Must Be a Number!");
            return 0;
        }
    }


}
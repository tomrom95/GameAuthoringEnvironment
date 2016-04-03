package gameauthoring;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public class TextFormData extends FormData{
    
    private TextField myData;
    
    public TextFormData (String title){
        super(title);
        this.setField(createTextField());
    }
    
    private Node createTextField () {
        myData = new TextField();
        return myData;
    }

    @Override
    public void update () {
        
    }

    @Override
    public String getData () {
        return myData.getText();
    }

}

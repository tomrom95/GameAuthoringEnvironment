package gameauthoring.creation.entryviews;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;

public abstract class InputEntryView extends EntryView {
    
    private GridPane myContainer; // TODO Magic Number and Factory
    private TextInputControl myTextInput;

    public InputEntryView (String label, IFormDataManager data, double width, double height, String cssClass) {
        super(label, data);
       
    }
    

    protected void init (String label, double width, double height, String cssClass) {
        this.myTextInput.setMinSize(width, height);
        this.myTextInput.setMaxSize(width, height);
        this.myTextInput.textProperty().bindBidirectional(getData().getValueProperty());
        this.myContainer = new GridPane();
        myContainer.add(new Label(myLabel), 0, 0);
        myContainer.add(myTextInput, 0, 1);
        myContainer.getStyleClass().add(cssClass);
        
    }
    
    protected void setInputControl(TextInputControl text){
        this.myTextInput = text;
    }

    @Override
    public Node draw () {
        return myContainer;
    }



}

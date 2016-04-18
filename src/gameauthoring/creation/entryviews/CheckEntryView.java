package gameauthoring.creation.entryviews;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CheckEntryView extends EntryView{
    private GridPane myContainer;
    private CheckBox myCheckBox;

    public CheckEntryView (String label, String cssClass) {
        super(label);
        myCheckBox = new CheckBox();
        
        myContainer = new GridPane();
        myContainer.getStyleClass().add(cssClass);
        myContainer.add(new Label(label), 0, 0);
        myContainer.add(myCheckBox, 1, 0);
        myCheckBox.setSelected(false);
    }
    
    public BooleanProperty isCheckedProperty(){
        return myCheckBox.selectedProperty();
    }
        
    public void setSelected(boolean isSelected){
        this.myCheckBox.setSelected(isSelected);
    }

    @Override
    public Node draw () {
        return myContainer;
    }

   

}

package gameauthoring.creation.entryviews;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CheckEntryView extends EntryView{
    private GridPane myContainer;
    private CheckBox myCheckBox;

    public CheckEntryView (String label, String cssClass) {
        super(label);
        myContainer = new GridPane();
        myContainer.getStyleClass().add(cssClass);
        myContainer.add(new Label(label), 0, 0);
        myCheckBox.setSelected(false);
    }
    
    public boolean isChecked(){
        return myCheckBox.isSelected();
    }

    @Override
    public Node draw () {
        return myContainer;
    }

   

}

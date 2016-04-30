package gameauthoring.creation.entryviews;

import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

/**
 * Entry box that presents a boolean true or false check box to the user
 * 
 * @author Joe Lilien
 *
 */
public class CheckEntryView extends EntryView{
    private CheckBox myCheckBox;

    public CheckEntryView (String label, String cssClass) {
        super(label,cssClass);
        myCheckBox = new CheckBox();        
        init();
    }
    
    @Override
    protected void initContainer(String cssClass) {
        setMyContainer(getMyFactory().makeHBox(20, Pos.CENTER, getLabel()));
        getMyContainer().getStyleClass().add(cssClass);
    }
    
    @Override
    protected void init(){
        getMyContainer().getChildren().add(myCheckBox);
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
        return getMyContainer();
    }   

}

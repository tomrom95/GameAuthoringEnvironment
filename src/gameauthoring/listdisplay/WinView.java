package gameauthoring.listdisplay;

import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IGame;
import engine.ILevel;
import engine.conditions.ICondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


public abstract class WinView extends SubConditionView {

    private ComboBox<AttributeType> myAttributeType;
    private ObservableList<AttributeType> myAttributeStorage;
    
    public WinView (ILevel level, ObservableList<AttributeType> attributeStorage) {
        super(level.getConditionsListProperty());
        myAttributeStorage = attributeStorage;
        initializeDisplay();
        
    }

    protected void initBoxes () {
        myAttributeType = createComboBox(myAttributeStorage);
    }

    @Override
    protected ICondition subCreation () {
       // return new OnAttributeCondition();
        return null;
    }

    @Override
    protected String getLabelKey (String key) {
        // TODO Auto-generated method stub
        return "GroupA";
    }

}

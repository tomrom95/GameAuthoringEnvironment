package gameauthoring.util;

import engine.profile.IProfilable;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ConditionUIFactory extends BasicUIFactory {
 
    @Override 
    public Button createButton(String title) {
        Button button = super.createButton(title);
        addStyling(button, "CButton");
        return button;
    }
    
    public <T extends IProfilable> ComboBox<T> createCombo (ObservableList<T> list) {
        ComboBox<T> box = super.createCombo(list);
        addStyling(box, "CButton");
        return box;
    }
    
    
}

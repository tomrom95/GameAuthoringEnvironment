package gameauthoring;

import java.util.List;
import javafx.collections.ObservableList;

public interface CreationController<ItemType> {
    /**
     * Gets observable list from model
     */
    void setItems(ObservableList<ItemType> items);
    
    void showAndEdit();
    
    void createBlank(); // Empty something
    
    void delete();
    
    void edit(List<SubFormView> subForms); 
}

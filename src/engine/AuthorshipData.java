package engine;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for holding the structures the user creates during authorship that want to be saved
 * @author RyanStPierre
 *
 */
public class AuthorshipData {

    ObservableList<ObjectProperty<ISprite>> myCreatedSprites;
    
    public AuthorshipData () {
        myCreatedSprites = FXCollections.observableArrayList();
    }
    
    public ObservableList<ObjectProperty<ISprite>> getCreatedSprites () {
        return myCreatedSprites;
    }
}

package engine;

import engine.definitions.SpriteDefinition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Class for holding the structures the user creates during authorship that want to be saved
 *
 * @author RyanStPierre
 *
 */
public class AuthorshipData {

    ObservableList<SpriteDefinition> myCreatedSprites;

    public AuthorshipData () {
        myCreatedSprites = FXCollections.observableArrayList();
    }

    public ObservableList<SpriteDefinition> getCreatedSprites () {
        return myCreatedSprites;
    }
}

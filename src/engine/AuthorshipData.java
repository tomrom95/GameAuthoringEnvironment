package engine;

import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.definitions.GroupDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.DefinitionCollection;
import javafx.collections.FXCollections;


/**
 * Class for holding the structures the user creates during authorship that want to be saved
 *
 * @author RyanStPierre, Jeremy Schreck, Joe Lilien
 *
 */
public class AuthorshipData {

    List<DefinitionCollection<SpriteDefinition>> myCreatedSprites;
    List<DefinitionCollection<AttributeDefinition>> myCreatedAttributes;
    List<DefinitionCollection<GroupDefinition>> myCreatedGroups;

    public AuthorshipData () {
        myCreatedSprites = FXCollections.observableArrayList();
        myCreatedAttributes = FXCollections.observableArrayList();
        myCreatedGroups = FXCollections.observableArrayList();
    }

    public List<DefinitionCollection<SpriteDefinition>> getMyCreatedSprites () {
        return myCreatedSprites;
    }

    public List<DefinitionCollection<AttributeDefinition>> getMyCreatedAttributes () {
        return myCreatedAttributes;
    }

    public List<DefinitionCollection<GroupDefinition>> getMyCreatedGroups () {
        return myCreatedGroups;
    }

}

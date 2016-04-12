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
    DefinitionCollection<AttributeDefinition> myCreatedAttributes;
    DefinitionCollection<GroupDefinition> myCreatedGroups;

    public AuthorshipData () {
        
        myCreatedSprites = FXCollections.observableArrayList();
        myCreatedAttributes = new DefinitionCollection<AttributeDefinition>("Attributes");
        myCreatedGroups = new DefinitionCollection<GroupDefinition>("Groups");
    }

    public List<DefinitionCollection<SpriteDefinition>> getMyCreatedSprites () {
        return myCreatedSprites;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedAttributes () {
        return myCreatedAttributes;
    }

    public DefinitionCollection<GroupDefinition> getMyCreatedGroups () {
        return myCreatedGroups;
    }

}

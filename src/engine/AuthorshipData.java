package engine;

import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.shareddata.DefinitionCollection;
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

    DefinitionCollection<SpriteGroup> myCreatedGroups;

    public AuthorshipData () {

        myCreatedSprites = FXCollections.observableArrayList();
    }

    public List<DefinitionCollection<SpriteDefinition>> getMyCreatedSprites () {
        return myCreatedSprites;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedAttributes () {
        return myCreatedAttributes;
    }

    public DefinitionCollection<SpriteGroup> getMyCreatedGroups () {
        return myCreatedGroups;
    }

    public void addCreatedSprites (DefinitionCollection<SpriteDefinition> createdSprites) {
        this.myCreatedSprites.add(createdSprites);
    }

    public void setMyCreatedAttributes (DefinitionCollection<AttributeDefinition> createdAttributes) {
        this.myCreatedAttributes = createdAttributes;
    }

    public void setMyCreatedGroups (DefinitionCollection<SpriteGroup> createdGroups) {
        this.myCreatedGroups = createdGroups;
    }

}

package engine;

import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.definitions.GroupDefinition;
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
    DefinitionCollection<SpriteDefinition> myCreatedMissiles;

    DefinitionCollection<AttributeDefinition> myCreatedAttributes;

    DefinitionCollection<GroupDefinition> myCreatedGroups;

    public AuthorshipData () {

        myCreatedSprites = FXCollections.observableArrayList();
    }

    public List<DefinitionCollection<SpriteDefinition>> getMyCreatedSprites () {
        return myCreatedSprites;
    }

    public DefinitionCollection<SpriteDefinition> getMyCreatedMissiles () {
        return myCreatedMissiles;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedAttributes () {
        return myCreatedAttributes;
    }

    public DefinitionCollection<GroupDefinition> getMyCreatedGroups () {
        return myCreatedGroups;
    }

    public void addCreatedSprites (DefinitionCollection<SpriteDefinition> createdSprites) {
        this.myCreatedSprites.add(createdSprites);
    }

    public void setMyCreatedMissiles (DefinitionCollection<SpriteDefinition> createdMissiles) {
        this.myCreatedMissiles = createdMissiles;
    }

    public void setMyCreatedAttributes (DefinitionCollection<AttributeDefinition> createdAttributes) {
        this.myCreatedAttributes = createdAttributes;
    }

    public void setMyCreatedGroups (DefinitionCollection<GroupDefinition> createdGroups) {
        this.myCreatedGroups = createdGroups;
    }

}

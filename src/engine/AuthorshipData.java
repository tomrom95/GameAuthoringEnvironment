package engine;

import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.definitions.EventPackageDefinition;
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
    DefinitionCollection<AttributeDefinition> myCreatedAttributes;
    DefinitionCollection<ISpriteGroup> myCreatedGroups;
    DefinitionCollection<EventPackageDefinition> myCreatedEventPackages;

    public AuthorshipData () {

        myCreatedSprites = FXCollections.observableArrayList();
        myCreatedAttributes =
                new DefinitionCollection<AttributeDefinition>("Created Attributes",
                                                              FXCollections.observableArrayList());
        myCreatedGroups = new DefinitionCollection<ISpriteGroup>("Created Groups",
                FXCollections.observableArrayList());
        myCreatedEventPackages = new DefinitionCollection<EventPackageDefinition>("Created Groups",
                FXCollections.observableArrayList());
    }

    public List<DefinitionCollection<SpriteDefinition>> getMyCreatedSprites () {
        return myCreatedSprites;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedAttributes () {
        return myCreatedAttributes;
    }
    
    public DefinitionCollection<EventPackageDefinition> getMyCreatedEventPackages () {
        return myCreatedEventPackages;
    }

    public DefinitionCollection<ISpriteGroup> getMyCreatedGroups () {
        return myCreatedGroups;
    }
    
    public void addCreatedSprites (DefinitionCollection<SpriteDefinition> createdSprites) {
        this.myCreatedSprites.add(createdSprites);
    }
    
    public void setMyCreatedEvents (DefinitionCollection<EventPackageDefinition> createdEvents) {
        this.myCreatedEventPackages = createdEvents;
    }

    public void setMyCreatedAttributes (DefinitionCollection<AttributeDefinition> createdAttributes) {
        this.myCreatedAttributes = createdAttributes;
    }

    public void setMyCreatedGroups (DefinitionCollection<ISpriteGroup> createdGroups) {
        this.myCreatedGroups = createdGroups;
    }

}

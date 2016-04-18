package engine;

import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.definitions.EventPackageDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.shareddata.DefinitionCollection;
import javafx.collections.FXCollections;


/**
 * Class for holding the structures the user creates during authorship that want to be saved
 *
 * @author RyanStPierre, Jeremy Schreck, Joe Lilien
 *         TODO if we want to have default collections, such as for missiles, we should specify that
 *         in a resource file
 */
public class AuthorshipData {

    private List<DefinitionCollection<SpriteDefinition>> myCreatedSprites;
    private DefinitionCollection<SpriteDefinition> myCreatedMissiles;

    private DefinitionCollection<AttributeDefinition> myCreatedGlobals;
    private DefinitionCollection<AttributeDefinition> myCreatedAttributes;
    private DefinitionCollection<SpriteGroup> myCreatedGroups;
    private DefinitionCollection<EventPackageDefinition> myCreatedEventPackages;

    public AuthorshipData () {

        myCreatedSprites = FXCollections.observableArrayList();
        
        myCreatedGlobals =
                new DefinitionCollection<AttributeDefinition>("Global Resources",
                                                              FXCollections.observableArrayList());
        myCreatedAttributes =
                new DefinitionCollection<AttributeDefinition>("Created Attributes",
                                                              FXCollections.observableArrayList());
        myCreatedGroups = new DefinitionCollection<SpriteGroup>("Created Groups",
                                                                FXCollections
                                                                        .observableArrayList());
        myCreatedEventPackages = new DefinitionCollection<EventPackageDefinition>("Created Groups",
                                                                                  FXCollections
                                                                                          .observableArrayList());
    }

    public List<DefinitionCollection<SpriteDefinition>> getMyCreatedSprites () {
        return myCreatedSprites;
    }

    public DefinitionCollection<SpriteDefinition> getMyCreatedMissiles () {
        return myCreatedMissiles;
    }
    
    public DefinitionCollection<AttributeDefinition> getMyCreatedGlobals () {
        return myCreatedGlobals;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedAttributes () {
        return myCreatedAttributes;
    }

    public DefinitionCollection<EventPackageDefinition> getMyCreatedEventPackages () {
        return myCreatedEventPackages;
    }

    public DefinitionCollection<SpriteGroup> getMyCreatedGroups () {
        return myCreatedGroups;
    }

    public void addCreatedSprites (DefinitionCollection<SpriteDefinition> createdSprites) {
        myCreatedSprites.add(createdSprites);
    }

    public void setMyCreatedEvents (DefinitionCollection<EventPackageDefinition> createdEvents) {
        myCreatedEventPackages = createdEvents;
    }

    public void setMyCreatedMissiles (DefinitionCollection<SpriteDefinition> createdMissiles) {
        myCreatedMissiles = createdMissiles;
    }

    public void setMyCreatedAttributes (DefinitionCollection<AttributeDefinition> createdAttributes) {
        myCreatedAttributes = createdAttributes;
    }

    public void setMyCreatedGroups (DefinitionCollection<SpriteGroup> createdGroups) {
        myCreatedGroups = createdGroups;
    }
    
    public void setMyCreatedGlobals (DefinitionCollection<AttributeDefinition> createdGlobals) {
        myCreatedGlobals = createdGlobals;
    }

}

package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.EventPackageDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.shareddata.DefinitionCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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

    private DefinitionCollection<WaveDefinition> myCreatedWaves;
    private DefinitionCollection<AttributeDefinition> myCreatedGlobals;
    private DefinitionCollection<AttributeDefinition> myCreatedAttributes;
    private DefinitionCollection<SpriteGroup> myCreatedGroups;
    private DefinitionCollection<EventPackageDefinition> myCreatedEventPackages;
    
    public AuthorshipData () {
        myCreatedSprites = FXCollections.observableArrayList();
        myCreatedWaves =
                new DefinitionCollection<WaveDefinition>("Wave Definitions",
                                                         FXCollections.observableArrayList());

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

    /**
     * Just for show and picking. Will not edit the overall lists!
     * 
     * @return all the created sprites
     */

    public ObservableList<SpriteDefinition> getAllCreatedSprites () {
        List<SpriteDefinition> sprites = new ArrayList<>();
        getMyCreatedSprites().stream().forEach(col -> sprites.addAll(col.getItems()));
        return FXCollections.observableArrayList(sprites);
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

    public DefinitionCollection<WaveDefinition> getCreatedWaves () {
        return myCreatedWaves;
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

    public void addWave (WaveDefinition waveDef) {
        myCreatedWaves.addItem(waveDef);
        
    }

}

package engine;

import java.util.ArrayList;
import java.util.List;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.EventPackageDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import engine.profile.Profile;
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

    // TODO: figure out better way of organizing authorhsip data and making it flexible
        // we may want these three lists 
        // created sprites would have all sprites (including spawners?)
        // group sprites would have all sprites minus spawners
        // level selector sprite would have all sprites plus spawners minus missiles

    private List<DefinitionCollection<SpriteDefinition>> myCreatedSprites;
    
    
    
    private List<DefinitionCollection<SpriteDefinition>> myGroupSprites;
    private List<DefinitionCollection<SpriteDefinition>> myLevelSelectorSprites;

    private DefinitionCollection<SpriteDefinition> myCreatedMissiles;
    private DefinitionCollection<WaveDefinition> myCreatedWaves;
    private DefinitionCollection<AttributeDefinition> myCreatedGlobals;
    private DefinitionCollection<AttributeDefinition> myCreatedAttributes;
    private DefinitionCollection<SpriteGroup> myCreatedGroups;
    private DefinitionCollection<EventPackageDefinition> myCreatedEventPackages;
    
    public AuthorshipData () {
        myCreatedSprites = FXCollections.observableArrayList();
        myCreatedSprites.add(new DefinitionCollection<SpriteDefinition>("Enemies", FXCollections.observableArrayList()));
        myCreatedSprites.add(new DefinitionCollection<SpriteDefinition>("Defenders", FXCollections.observableArrayList()));

        myGroupSprites = FXCollections.observableArrayList();
        myLevelSelectorSprites = FXCollections.observableArrayList();
        
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
        myCreatedMissiles = new DefinitionCollection<>("Missiles", FXCollections.observableArrayList());
    }

    //TODO why doesnt this work
    private void init () {
        SpriteGroup spriteGroup = new SpriteGroup(new ArrayList<>());
        spriteGroup.setProfile(new Profile("Empty Group"));
        myCreatedGroups.addItem(spriteGroup);
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

    // Getters and setters
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

    public DefinitionCollection<WaveDefinition> getCreatedWaves () {
        return myCreatedWaves;
    }

    public void addCreatedSprites (DefinitionCollection<SpriteDefinition> createdSprites) {
        myCreatedSprites.add(createdSprites);        
    }

    public List<DefinitionCollection<SpriteDefinition>> getMyGroupSprites () {
        return myGroupSprites;
    }

    public void addGroupSprites (DefinitionCollection<SpriteDefinition> groupSprites) {
        this.myGroupSprites.add(groupSprites);
    }

    public List<DefinitionCollection<SpriteDefinition>> getMyLevelSelectorSprites () {
        return myLevelSelectorSprites;
    }

    public void addLevelSelectorSprites (DefinitionCollection<SpriteDefinition> levelSelectorSprites) {
        this.myLevelSelectorSprites.add(levelSelectorSprites);
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

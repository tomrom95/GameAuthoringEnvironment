package engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 *         
 */
public class AuthorshipData {
    
    private Map<String, DefinitionCollection<SpriteDefinition>> myCreatedSprites;
    
    private DefinitionCollection<SpriteDefinition> myCreatedMissiles;
    private DefinitionCollection<WaveDefinition> myCreatedWaves;
    private DefinitionCollection<AttributeDefinition> myCreatedGlobals;
    private DefinitionCollection<AttributeDefinition> myCreatedAttributes;
    private DefinitionCollection<SpriteGroup> myCreatedGroups;
    private DefinitionCollection<EventPackageDefinition> myCreatedEventPackages;
    
    public AuthorshipData () {
        myCreatedSprites = new HashMap<>();
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

    public ObservableList<SpriteDefinition> getAllCreatedSpritesAsList () {
        List<SpriteDefinition> sprites = new ArrayList<>();
        getMyCreatedSpritesMap().values().stream().forEach(col -> sprites.addAll(col.getItems()));
        return FXCollections.observableArrayList(sprites);
    }

    // Getters and setters
    public Map<String, DefinitionCollection<SpriteDefinition>> getMyCreatedSpritesMap () {
        return myCreatedSprites;
    }
    
    //TODO: language title thing
    public DefinitionCollection<SpriteDefinition> getMyCreatedSprites(String key) {
        if(!myCreatedSprites.containsKey(key)) myCreatedSprites.put(key, new DefinitionCollection<>("Sprites", FXCollections.observableArrayList()));
        return myCreatedSprites.get(key);
    }
    
    public DefinitionCollection<SpriteDefinition> getMyCreatedMissiles () {
        if(myCreatedMissiles == null) myCreatedMissiles = new DefinitionCollection<>("Missiles", FXCollections.observableArrayList());
        return myCreatedMissiles;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedGlobals () {
        if(myCreatedGlobals == null) myCreatedGlobals = new DefinitionCollection<>("Global Attributes", FXCollections.observableArrayList());
        return myCreatedGlobals;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedAttributes () {
        if(myCreatedAttributes == null) myCreatedAttributes = new DefinitionCollection<>("Character Attributes", FXCollections.observableArrayList());
        return myCreatedAttributes;
    }

    public DefinitionCollection<EventPackageDefinition> getMyCreatedEventPackages () {
        if(myCreatedEventPackages == null) myCreatedEventPackages = new DefinitionCollection<>("Event Packages", FXCollections.observableArrayList());
        return myCreatedEventPackages;
    }

    public DefinitionCollection<SpriteGroup> getMyCreatedGroups () {
        if(myCreatedGroups == null) myCreatedGroups = new DefinitionCollection<>("Groups", FXCollections.observableArrayList());
        return myCreatedGroups;
    }

    public DefinitionCollection<WaveDefinition> getCreatedWaves () {
        if(myCreatedWaves == null) myCreatedWaves = new DefinitionCollection<>("Waves", FXCollections.observableArrayList());
        return myCreatedWaves;
    }
    public DefinitionCollection<SpriteDefinition> getMyCreatedSprites(String key, String title) {
        if(!myCreatedSprites.containsKey(key)) myCreatedSprites.put(key, new DefinitionCollection<>(title, FXCollections.observableArrayList()));
        return myCreatedSprites.get(key);
    }
    
    public DefinitionCollection<SpriteDefinition> getMyCreatedMissiles (String title) {
        if(myCreatedMissiles == null) myCreatedMissiles = new DefinitionCollection<>(title, FXCollections.observableArrayList());
        return myCreatedMissiles;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedGlobals (String title) {
        if(myCreatedGlobals == null) myCreatedGlobals = new DefinitionCollection<>(title, FXCollections.observableArrayList());
        return myCreatedGlobals;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedAttributes (String title) {
        if(myCreatedAttributes == null) myCreatedAttributes = new DefinitionCollection<>(title, FXCollections.observableArrayList());
        return myCreatedAttributes;
    }

    public DefinitionCollection<EventPackageDefinition> getMyCreatedEventPackages (String title) {
        if(myCreatedEventPackages == null) myCreatedEventPackages = new DefinitionCollection<>(title, FXCollections.observableArrayList());
        return myCreatedEventPackages;
    }

    public DefinitionCollection<SpriteGroup> getMyCreatedGroups (String title) {
        if(myCreatedGroups == null) myCreatedGroups = new DefinitionCollection<>(title, FXCollections.observableArrayList());
        return myCreatedGroups;
    }

    public DefinitionCollection<WaveDefinition> getCreatedWaves (String title) {
        if(myCreatedWaves == null) myCreatedWaves = new DefinitionCollection<>(title, FXCollections.observableArrayList());
        return myCreatedWaves;
    }

    public void addWave (WaveDefinition waveDef) {
        myCreatedWaves.addItem(waveDef);

    }

}

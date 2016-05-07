package engine;

import java.util.ArrayList;
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

    private static String MISSILES_TITLE_KEY = "Missiles";
    private static String WAVES_TITLE_KEY = "Waves";
    private static String GLOBALS_TITLE_KEY = "Globals";
    private static String ATTRIBUTES_TITLE_KEY = "Attributes";
    private static String GROUPS_TITLE_KEY = "Groups";
    private static String EVENTPACKAGES_TITLE_KEY = "EventPackages";

    private DefinitionCollection<SpriteDefinition> myCreatedMissiles;
    private DefinitionCollection<WaveDefinition> myCreatedWaves;
    private DefinitionCollection<AttributeDefinition> myCreatedGlobals;
    private DefinitionCollection<AttributeDefinition> myCreatedAttributes;
    private DefinitionCollection<SpriteGroup> myCreatedGroups;
    private DefinitionCollection<EventPackageDefinition> myCreatedEventPackages;

    public AuthorshipData () {
        myCreatedSprites = new HashMap<>();
    }

    // TODO why doesnt this work
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

    public DefinitionCollection<SpriteDefinition> getMyCreatedSprites (String key) {
        if (!myCreatedSprites.containsKey(key)) {
            myCreatedSprites
                    .put(key, new DefinitionCollection<>(key, FXCollections.observableArrayList()));
        }
        return myCreatedSprites.get(key);
    }

    public DefinitionCollection<SpriteDefinition> getMyCreatedMissiles () {
        if (myCreatedMissiles == null) {
            myCreatedMissiles =
                    new DefinitionCollection<>(AuthorshipData.MISSILES_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedMissiles;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedGlobals () {
        if (myCreatedGlobals == null) {
            myCreatedGlobals =
                    new DefinitionCollection<>(AuthorshipData.GLOBALS_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedGlobals;
    }

    public DefinitionCollection<AttributeDefinition> getMyCreatedAttributes () {
        if (myCreatedAttributes == null) {
            myCreatedAttributes =
                    new DefinitionCollection<>(AuthorshipData.ATTRIBUTES_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedAttributes;
    }

    public DefinitionCollection<EventPackageDefinition> getMyCreatedEventPackages () {
        if (myCreatedEventPackages == null) {
            myCreatedEventPackages =
                    new DefinitionCollection<>(AuthorshipData.EVENTPACKAGES_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedEventPackages;
    }

    public DefinitionCollection<SpriteGroup> getMyCreatedGroups () {
        if (myCreatedGroups == null) {
            myCreatedGroups =
                    new DefinitionCollection<>(AuthorshipData.GROUPS_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedGroups;
    }

    public DefinitionCollection<WaveDefinition> getCreatedWaves () {
        if (myCreatedWaves == null) {
            myCreatedWaves =
                    new DefinitionCollection<>(AuthorshipData.WAVES_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedWaves;
    }

    public void addWave (WaveDefinition item) {
        getCreatedWaves().addItem(item);
    }

    public void removeFromGroups (SpriteDefinition def) {
        getMyCreatedGroups().getItems().forEach(e -> e.remove(def));
    }

    public void removeFromAttributes (AttributeDefinition myLastItem) {
        getAllCreatedSpritesAsList().forEach(e -> e.removeAttribute(myLastItem));
    }

}

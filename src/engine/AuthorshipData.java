package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.EventPackageDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.util.ListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Class for holding the structures the user creates during authorship that want to be saved
 *
 * @author RyanStPierre, Jeremy Schreck, Joe Lilien
 *
 */
public class AuthorshipData {

    private Map<String, ListWrapper<SpriteDefinition>> myCreatedSprites;

    private static String MISSILES_TITLE_KEY = "Missiles";
    private static String WAVES_TITLE_KEY = "Waves";
    private static String GLOBALS_TITLE_KEY = "Globals";
    private static String ATTRIBUTES_TITLE_KEY = "Attributes";
    private static String GROUPS_TITLE_KEY = "Groups";
    private static String EVENTPACKAGES_TITLE_KEY = "EventPackages";

    private ListWrapper<SpriteDefinition> myCreatedMissiles;
    private ListWrapper<WaveDefinition> myCreatedWaves;
    private ListWrapper<AttributeDefinition> myCreatedGlobals;
    private ListWrapper<AttributeDefinition> myCreatedAttributes;
    private ListWrapper<SpriteGroup> myCreatedGroups;
    private ListWrapper<EventPackageDefinition> myCreatedEventPackages;

    public AuthorshipData () {
        myCreatedSprites = new HashMap<>();
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
    public Map<String, ListWrapper<SpriteDefinition>> getMyCreatedSpritesMap () {
        return myCreatedSprites;
    }

    public ListWrapper<SpriteDefinition> getMyCreatedSprites (String key) {
        if (!myCreatedSprites.containsKey(key)) {
            myCreatedSprites
                    .put(key, new ListWrapper<>(key, FXCollections.observableArrayList()));
        }
        return myCreatedSprites.get(key);
    }

    public ListWrapper<SpriteDefinition> getMyCreatedMissiles () {
        if (myCreatedMissiles == null) {
            myCreatedMissiles =
                    new ListWrapper<>(AuthorshipData.MISSILES_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedMissiles;
    }

    public ListWrapper<AttributeDefinition> getMyCreatedGlobals () {
        if (myCreatedGlobals == null) {
            myCreatedGlobals =
                    new ListWrapper<>(AuthorshipData.GLOBALS_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedGlobals;
    }

    public ListWrapper<AttributeDefinition> getMyCreatedAttributes () {
        if (myCreatedAttributes == null) {
            myCreatedAttributes =
                    new ListWrapper<>(AuthorshipData.ATTRIBUTES_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedAttributes;
    }

    public ListWrapper<EventPackageDefinition> getMyCreatedEventPackages () {
        if (myCreatedEventPackages == null) {
            myCreatedEventPackages =
                    new ListWrapper<>(AuthorshipData.EVENTPACKAGES_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedEventPackages;
    }

    public ListWrapper<SpriteGroup> getMyCreatedGroups () {
        if (myCreatedGroups == null) {
            myCreatedGroups =
                    new ListWrapper<>(AuthorshipData.GROUPS_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedGroups;
    }

    public ListWrapper<WaveDefinition> getCreatedWaves () {
        if (myCreatedWaves == null) {
            myCreatedWaves =
                    new ListWrapper<>(AuthorshipData.WAVES_TITLE_KEY,
                                               FXCollections.observableArrayList());
        }
        return myCreatedWaves;
    }

    public void addWave (WaveDefinition item) {
        getCreatedWaves().getItems().add(item);
    }

    public void removeFromGroups (SpriteDefinition def) {
        getMyCreatedGroups().getItems().forEach(e -> e.remove(def));
    }

    public void removeFromAttributes (AttributeDefinition myLastItem) {
        getAllCreatedSpritesAsList().forEach(e -> e.removeAttribute(myLastItem));
    }

}

package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.definitions.concrete.SpriteDefinition;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.profile.Profile;
import engine.sprite.SpriteType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This class implements ISpriteGroup and holds the notion of a sprite group. A sprite groups is a
 * collection of sprites that are deemed to contain the same attributes and behaviors.
 *
 * @author Joe Lilien, Jeremy Schreck
 *
 */
public class SpriteGroup implements ISpriteGroup, IProfilable {

    private List<SpriteDefinition> mySpriteDefinitions = new ArrayList<>();
    private List<SpriteType> mySpriteTypes = new ArrayList<>();
    private IProfile myProfile;

    public SpriteGroup () {
        myProfile = new Profile();
    }
    
    public SpriteGroup (SpriteDefinition def, IProfile profile) {
        setSpriteDefinitions(new ArrayList<>(Arrays.asList(def)));
        myProfile = profile;
    }

    public SpriteGroup (List<SpriteDefinition> spriteDefs, IProfile profile) {
        setSpriteDefinitions(spriteDefs);
        myProfile = profile;
        System.out.println(spriteDefs.toString());
    }
    public SpriteGroup (List<SpriteDefinition> spriteDefs) {
        setSpriteDefinitions(spriteDefs);
        myProfile = new Profile();
    }
    

    public List<SpriteDefinition> getSpriteDefinitions () {
        return mySpriteDefinitions;
    }

    //TODO: watch out for weird behavior with this change
    public void setSpriteDefinitions (List<SpriteDefinition> sprites) {
        mySpriteDefinitions = sprites;      
        mySpriteTypes = new ArrayList<>();
        for (SpriteDefinition s : mySpriteDefinitions) {
            mySpriteTypes.add(s.getSpriteType());
        }
    }

    @Override
    public boolean contains (SpriteType spriteType) {
        return getSpriteTypes().contains(spriteType);
    }

    @Override
    public ObservableList<SpriteType> getSpriteTypes () {
        return FXCollections.observableArrayList(mySpriteTypes);
    }

    @Override
    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;
    }

    public void remove (SpriteDefinition def) {
        mySpriteDefinitions.remove(def);
    }

}

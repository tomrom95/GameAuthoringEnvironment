package engine;

import java.util.ArrayList;
import java.util.List;
import engine.definitions.SpriteDefinition;
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

    private List<SpriteDefinition> mySpriteDefinitions;
    private List<SpriteType> mySpriteTypes;
    private IProfile myProfile;

    public SpriteGroup () {
        mySpriteDefinitions = new ArrayList<>();
        myProfile = new Profile();
    }

    public SpriteGroup (List<SpriteDefinition> spriteTypes) {
        mySpriteDefinitions = spriteTypes;
        myProfile = new Profile();
    }

    public List<SpriteDefinition> getSpriteDefinitions () {
        return mySpriteDefinitions;
    }

    public void setSpriteDefinitions (List<SpriteDefinition> sprites) {
        mySpriteDefinitions = new ArrayList<>(sprites);

        // Patch solution for now, need to work out in more detail later

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

}

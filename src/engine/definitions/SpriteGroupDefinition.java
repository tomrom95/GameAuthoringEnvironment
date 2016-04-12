package engine.definitions;

import engine.ISpriteGroup;
import engine.profile.IProfilable;
import engine.profile.IProfile;


/**
 * We can either have this class and the reflective recursive code generate this stuff
 * on the fly, or we can have it point to a List that has been previously created
 * somewhere in the models for the front end, in another tab/controller/view pair
 * that is dedicated solely to creating and storing the sprite groups
 *
 * Using the index value, or some other form of communication i think will be better
 * 
 * @author jonathanim, Jeremy Schreck, Joe Lilien
 *
 */
public class SpriteGroupDefinition implements IProfilable {

    private int mySpriteGroupIndexVal;
    private IProfile myProfile;

    public ISpriteGroup create () {
        return getSpriteGroupForIndex(mySpriteGroupIndexVal);
    }

    private ISpriteGroup getSpriteGroupForIndex (int index) {
        return null;
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

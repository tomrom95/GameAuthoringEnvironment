package engine;

import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.profile.Profile;


/**
 * This class represents an in-game type that is based on a string backing. It handles equality
 * checks based off this string
 *
 */
public abstract class StringBasedType implements IProfilable {

    private String myType;
    private IProfile myProfile;

    public StringBasedType (String type) {
        myType = type;
        myProfile = new Profile(myType);
    }

    @Override
    public String toString () {
        return getType();
    }

    /**
     * Overrides the equality check for this object using the label for this type
     */
    @Override
    public boolean equals (Object obj) {
        if (obj == this) {
            return true;
        }
        if (!isSameClass(obj)) {
            return false;
        }
        StringBasedType otherType = (StringBasedType) obj;

        return getType().equals(otherType.getType());
    }

    protected abstract boolean isSameClass (Object obj);

    /**
     * Overrides the hashCode check for this object to only use the label for this type
     */
    @Override
    public int hashCode () {
        return getType().hashCode();
    }

    public String getType () {
        return myType;
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

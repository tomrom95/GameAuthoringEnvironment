package engine.definitions;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.profile.Profile;


/**
 * Definition for Attribute. Have getter/setter methods for each attribute.
 * 
 * @author Jin An, Jeremy Schreck, Joe Lilien
 *
 */

public class AttributeDefinition implements IProfilable {

    private String myType;
    private boolean myIsGlobal;
    private double myStartingValue;
    private IProfile myProfile;

    /**
     * Constructor initialized with defaults
     */
    public AttributeDefinition () {
        myType = "";
        myIsGlobal = false;
        myStartingValue = 0;
        myProfile = new Profile();
    }

    public IAttribute create () {
        return new Attribute(myStartingValue, new AttributeType(myType));
    }

    public void setType (String type) {
        myType = type;
    }

    public void setIsGlobal (boolean bool) {
        myIsGlobal = bool;
    }

    public String getType () {
        return myType;
    }

    public boolean getIsGlobal () {
        return myIsGlobal;
    }

    public double getStartingValue () {
        return myStartingValue;
    }

    public void setStartingValue (double startingValue) {
        myStartingValue = startingValue;
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

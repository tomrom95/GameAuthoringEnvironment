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

    //TODO: implement min/max value functionality
    private String myType;
    private double myStartingValue;
    private IProfile myProfile;
    private boolean isLevelSpecific;

    /**
     * Constructor initialized with defaults
     * TODO put defaults in resource file
     */
    public AttributeDefinition () {
        myType = "";
        myStartingValue = 0;
        myProfile = new Profile();
    }

    public IAttribute create () {
        return new Attribute(myStartingValue, new AttributeType(myType));
    }

    public void setType (String type) {
        myType = type;
    }

    public String getType () {
        return myType;
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

    public boolean isLevelSpecific () {
        return isLevelSpecific;
    }

    public void setIsLevelSpecific (boolean level) {
        isLevelSpecific = level;
    }

}

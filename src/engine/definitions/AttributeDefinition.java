package engine.definitions;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.profile.IProfile;


/**
 * Definition for Attribute. Have getter/setter methods for each attribute.
 * 
 * @author Jin An
 *
 */

public class AttributeDefinition implements IDefinition {

    private String myType;
    private boolean myIsGlobal;
    private AttributeDefinition myAttributeDefinition;
    private double myStartingValue;
    private IProfile myProfile;

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

    public AttributeDefinition getAttributeDefinition () {
        return myAttributeDefinition;
    }

    public void setAttributeDefinition (AttributeDefinition attributeDef) {
        this.myAttributeDefinition = attributeDef;
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

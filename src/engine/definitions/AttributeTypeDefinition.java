package engine.definitions;

import engine.AttributeType;
import engine.profile.IProfile;


/**
 * Can either get and set, or once again have reference to the object from which we query with index
 * values
 * 
 * @author jonathanim
 *
 */
public class AttributeTypeDefinition  {

    // private AttributeType myAttributeType;
    private String myAttyType;
    private IProfile myProfile;

    public AttributeType create () {
        return new AttributeType(myAttyType);
    }

    public void setAttributeType (String toSet) {
        myAttyType = toSet;
    }
<<<<<<< HEAD
    
    
    public ProfileDefinition getProfileDefinition () {
        // TODO Auto-generated method stub
        return null;
    }

    
    public void setProfileDefinition (ProfileDefinition profileDef) {
        // TODO Auto-generated method stub
        
=======

    public String getAttributeType () {
        return myAttyType;
>>>>>>> 5c956e7a2a513d379853c626b1bd2924f5d9bd91
    }


}

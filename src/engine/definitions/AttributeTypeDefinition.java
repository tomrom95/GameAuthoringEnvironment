package engine.definitions;

import engine.AttributeType;


/**
 * Can either get and set, or once again have reference to the object from which we query with index
 * values
 * 
 * @author jonathanim
 *
 */
public class AttributeTypeDefinition implements IDefinition {

    private AttributeType myAttributeType;

    public AttributeType create () {
        return myAttributeType;
    }
    
    @Override
    public ProfileDefinition getProfileDefinition () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setProfileDefinition (ProfileDefinition profileDef) {
        // TODO Auto-generated method stub
        
    }
}

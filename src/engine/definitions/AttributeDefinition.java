package engine.definitions;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;


public class AttributeDefinition implements IDefinition {

    private String myType;
    private double myValue;

    public IAttribute create () {
        return new Attribute(myValue, new AttributeType(myType));
    }

    public void setType (String type) {
        myType = type;
    }

    public void setValue (double value) {
        myValue = value;
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

package engine.definitions;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;

/**
 * Definition for Attribute. Have getter/setter methods for each attribute.
 * @author Jin An
 *
 */

public class AttributeDefinition extends ProfileDefinition implements IDefinition {

    private String myType = "";
    private double myMaxValue, myMinValue;
    private boolean myIsGlobal;
    private AttributeDefinition myAttributeDefinition;
    
    public AttributeDefinition(){
        
    }
    public AttributeDefinition (double max, double min, boolean isGlobal){
        super("", "", "");
        // TODO Set a default. THis is just for view testing
        setMaxValue(max);
        setMinValue(min);
        setIsGlobal(isGlobal);
    }

    
    //TODO: Work on Attribute Class with engine group so that it can deal with max/min/isglobal
    public IAttribute create () {
        return new Attribute(myMaxValue, new AttributeType(myType));
    }

    public void setType (String type) {
        myType = type;
    }

    public void setMaxValue (double value) {
        myMaxValue = value;
    }

    public void setMinValue (double value) {
        myMinValue = value;
    }

    public void setIsGlobal (boolean bool) {
        myIsGlobal = bool;
    }

    public String getType () {
        return myType;
    }

    public double getMaxValue () {
        return myMaxValue;
    }

    public double getMinValue () {
        return myMinValue;
    }

    public boolean getIsGlobal () {
        return myIsGlobal;
    }

    public AttributeDefinition getAttributeDefinition () {
        return myAttributeDefinition;
    }
    
    public void setAttributeDefinition(AttributeDefinition attributeDef){
        this.myAttributeDefinition = attributeDef;

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

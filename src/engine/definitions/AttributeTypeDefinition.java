package engine.definitions;

import engine.AttributeType;


/**
 * Can either get and set, or once again have reference to the object from which we query with index
 * values
 *
 * @author jonathanim
 *
 */
public class AttributeTypeDefinition {

    private String myAttyType;

    public AttributeType create () {
        return new AttributeType(myAttyType);
    }

    public void setAttributeType (String toSet) {
        myAttyType = toSet;
    }

    public String getAttributeType () {
        return myAttyType;
    }

}

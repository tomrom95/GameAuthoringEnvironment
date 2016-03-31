package engine;

/**
 * This class defines the type of an attribute, and defines the functionality that allows checking
 * to see if attributes are of the same type
 * 
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public class AttributeType {

    private String myType;

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
        if (!(obj instanceof AttributeType)) {
            return false;
        }
        AttributeType otherType = (AttributeType) obj;

        return getType().equals(otherType.getType());
    }

    @Override
    public int hashCode () {
        return getType().hashCode();
    }

    private String getType () {
        return myType;
    }

}

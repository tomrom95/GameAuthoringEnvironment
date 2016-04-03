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
public class AttributeType extends StringBasedType {
    @Override
    protected boolean isSameClass (Object obj) {
        return obj instanceof AttributeType;
    }

}

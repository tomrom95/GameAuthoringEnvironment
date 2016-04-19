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

    // TODO should these be partially placed in Enums?
    public static final AttributeType CONSTANT = new AttributeType("Constant");
    public static final AttributeType X_VEL = new AttributeType("XVelocity");
    public static final AttributeType Y_VEL = new AttributeType("YVelocity");
    public static final AttributeType SPEED = new AttributeType("Speed");
    public static final AttributeType AMMO = new AttributeType("Ammo");
    public static final AttributeType FIRE_RATE = new AttributeType("Firerate");

    public AttributeType (String type) {
        super(type);
    }

    @Override
    protected boolean isSameClass (Object obj) {
        return obj instanceof AttributeType;
    }


}

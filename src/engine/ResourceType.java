package engine;

public class ResourceType extends StringBasedType {

    /**
     * Overrides the equality check for this object using the label for this type
     */
    @Override
    public boolean equals (Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResourceType)) {
            return false;
        }
        ResourceType otherType = (ResourceType)obj;

        return getType().equals(otherType.getType());
    }
}

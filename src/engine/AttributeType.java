package engine;

public class AttributeType {

    private String myType;

    @Override
    public String toString () {
        return getType();
    }

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

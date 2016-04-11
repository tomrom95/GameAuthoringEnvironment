package engine;

public abstract class StringBasedType {

    private String myType;

    public StringBasedType (String type) {
        myType = type;
    }

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
        if (!isSameClass(obj)) {
            return false;
        }
        StringBasedType otherType = (StringBasedType) obj;

        return getType().equals(otherType.getType());
    }

    protected abstract boolean isSameClass (Object obj);

    /**
     * Overrides the hashCode check for this object to only use the label for this type
     */
    @Override
    public int hashCode () {
        return getType().hashCode();
    }

    public String getType () {
        return myType;
    }
}

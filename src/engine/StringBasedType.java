package engine;

public abstract class StringBasedType {
    private String myType;

    @Override
    public String toString () {
        return getType();
    }

    /**
     * Overrides the equality check for this object using the label for this type
     */
    @Override
    public abstract boolean equals (Object obj);

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

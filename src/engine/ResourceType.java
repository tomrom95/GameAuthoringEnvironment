package engine;

/**
 * This class represents the type of a resource, with the appropriate implementation to allow for
 * equality tests
 *
 */

public class ResourceType extends StringBasedType {

    public ResourceType (String type) {
        super(type);
    }

    @Override
    protected boolean isSameClass (Object obj) {
        return obj instanceof ResourceType;
    }
}

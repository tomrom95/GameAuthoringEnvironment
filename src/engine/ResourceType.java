package engine;

public class ResourceType extends StringBasedType {
    @Override
    protected boolean isSameClass (Object obj) {
        return obj instanceof ResourceType;
    }
}

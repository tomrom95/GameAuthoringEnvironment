package engine;

public class ResourceType extends StringBasedType {
    
    public ResourceType(String type) {
        super(type);
    }
    
    @Override
    protected boolean isSameClass (Object obj) {
        return obj instanceof ResourceType;
    }
}

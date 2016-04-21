package engine.aipathing;

import engine.StringBasedType;

public class PathFlag extends StringBasedType {
    
    public static final PathFlag VISITED = new PathFlag("Visited");
    public static final PathFlag OPEN = new PathFlag("Open");

    public PathFlag (String type) {
        super(type);
    }

    @Override
    protected boolean isSameClass (Object obj) {
        return obj instanceof PathFlag;
    }

}

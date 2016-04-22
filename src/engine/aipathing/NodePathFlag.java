package engine.aipathing;

import engine.StringBasedType;

public class NodePathFlag extends StringBasedType {
    
    public static final NodePathFlag VISITED = new NodePathFlag("Visited");
    public static final NodePathFlag OPEN = new NodePathFlag("Open");

    public NodePathFlag (String type) {
        super(type);
    }

    @Override
    protected boolean isSameClass (Object obj) {
        return obj instanceof NodePathFlag;
    }

}

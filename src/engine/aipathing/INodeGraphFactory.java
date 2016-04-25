package engine.aipathing;

public interface INodeGraphFactory {

    /**
     * Will create a discrete node based representation of the 
     * IGame object, by sampling the board for obstructions at
     * a certain resolution, and then performing edge case checks
     * to ensure that gaps that are smaller than this resolution
     * are preserved
     * @return A {@link INodeGraph graph} discrete 
     * representation of the IGame state
     */
    INodeGraph getConstructedGraph ();
}

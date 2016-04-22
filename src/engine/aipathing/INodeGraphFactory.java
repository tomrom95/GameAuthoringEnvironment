package engine.aipathing;

public interface INodeGraphFactory {

    /**
     * @return A {@link INodeGraph graph} based upon other inputs
     *         in the constructor of the concrete class
     */
    INodeGraph getConstructedGraph ();
}

package engine.aipathing;

public interface IHeuristic {
    /**
     * Will return a cost estimate of reaching the goal node from the input
     * start node, potentially using the overall graph object if further
     * information is required
     * 
     * @param start
     * @param goal
     * @param graph
     * @return
     */
    double estimateCost (IPathNode start, IPathNode goal, INodeGraph graph);
}

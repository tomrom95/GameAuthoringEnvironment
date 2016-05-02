package engine.aipathing;

import util.Coordinate;

public interface INodeGraphFactory {

    /**
     * Will create a discrete node based representation of the 
     * IGame object, by sampling the board for obstructions at
     * a certain resolution, and then performing edge case checks
     * to ensure that gaps that are smaller than this resolution
     * are preserved
     * @param start Force the existence of a node at the start location
     * @param goal Force the existence of a node at the goal location
     * @return A {@link INodeGraph graph} discrete 
     * representation of the IGame state
     * 
     */
    INodeGraph getConstructedGraph (Coordinate start, Coordinate goal);
}

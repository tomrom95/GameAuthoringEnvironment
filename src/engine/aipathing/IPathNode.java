package engine.aipathing;

import java.util.List;
import util.Coordinate;


/**
 * A traversable point in the graph
 * 
 * @author jonathanim
 *
 */
public interface IPathNode {
    
    /**
     * The nodes that are considered connected in the overall graph 
     * to this node
     * @return list of other IPathNodes
     */
    List<IPathNode> getNeighbors ();
    
    /**
     * To support pathing algorithms that required marking
     * the map, we will support flags on each node
     * @return
     */
    List<NodePathFlag> getFlags ();

    /**
     * @return Copied version of the coordinate in the virtual
     *         game grid associated with the node being considered
     */
    Coordinate getLocation ();

}

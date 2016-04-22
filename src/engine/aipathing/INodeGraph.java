package engine.aipathing;

import java.util.List;
import java.util.function.Consumer;
import util.Coordinate;


/**
 * An internal data object to used by the {@link INodeGraphPather pathing} classes
 * when planning routes
 * 
 * @author jonathanim
 *
 */
public interface INodeGraph {

    /**
     * Only one node can exist at each unique coordinate location
     * This method will either simply add this node, or will overwrite
     * the node and properly replace references in all other nodes
     * 
     * @param node The IPathNode to add
     */
    void addNode (IPathNode node);

    /**
     * Will compare coordinate values of internally held nodes and if
     * one is the same as the input node, then will remove that node
     * and all references to that node from the graph
     * 
     * @param node Removal will be based upon coordinate comparison
     */
    void removeNode (IPathNode node);
    
    /**
     * True if the node if the coordinate point already is represented
     * by a node in the graph
     * @param node upon which to perform the check
     * @return true if contained, false if not
     */
    boolean containsNode (IPathNode node);

    /**
     * 
     * @param loc The point around which to start searching
     * @param maxDistance The radius of the search (inclusive)
     * @return List of all nodes that are within the specified distance of the input coordinate
     */
    List<IPathNode> getNodesNear (Coordinate loc, double maxDistance);

    /**
     * The internal list of all coordinates which constitute the graph
     * 
     * @return list of graph IPathNodes
     */
    List<IPathNode> getNodes ();

    /**
     * Will iterate through all existing nodes and consume
     * using the provided function
     * 
     * @param toApply
     */
    void applyToNodes (Consumer<IPathNode> toApply);

}

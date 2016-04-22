package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import util.Coordinate;


public class NodeGraph implements INodeGraph {

    private List<IPathNode> myNodes;

    public NodeGraph () {
        myNodes = new ArrayList<>();
    }

    @Override
    public void addNode (IPathNode node) {
        removeNode(node);
        getNodes().add(node);
    }

    @Override
    public void removeNode (IPathNode node) {
        if (getNodes().contains(node)) {
            removeAllReferencesToNode(node, getNodes());
        }
    }

    @Override
    public List<IPathNode> getNodesNear (Coordinate loc, double maxDistance) {
        return getNodesWithFilter(node -> Coordinate.distance(loc, node.getLocation()) <= maxDistance);
    }

    public List<IPathNode> getNodesWithFilter (Predicate<IPathNode> filter) {
        return getNodes().stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<IPathNode> getNodes () {
        return myNodes;
    }

    @Override
    public void applyToNodes (Consumer<IPathNode> toApply) {
        getNodes().stream().forEach(toApply);
    }

    /**
     * Will remove all references in all other nodes to the input node,
     * will then remove the node itself
     * 
     * @param node
     * @param listToClean
     */
    private void removeAllReferencesToNode (IPathNode node, List<IPathNode> listToClean) {
        if (node == null) {
            return;
        }
        listToClean.stream().forEach(n -> removeNeighborReferences(node, n));
        listToClean.remove(node);
    }

    private void removeNeighborReferences (IPathNode toRemove, IPathNode fromMyNeighbors) {
        fromMyNeighbors.getNeighbors().remove(toRemove);
    }

}

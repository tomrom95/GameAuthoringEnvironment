package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import util.Coordinate;


public class NodeGraph implements INodeGraph {

    private List<IPathNode> myNodes;
    private IPathNode[][] myPlacedNodes;

    public NodeGraph (IPathNode[][] nodes) {
        myNodes = new ArrayList<>();
        myPlacedNodes = nodes;
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
        return getNodesWithFilter(node -> Coordinate.distance(loc,
                                                              node.getLocation()) <= maxDistance);
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
     * Will remove all references in all other nodes to the input node
     * Will then remove the node itself
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

    @Override
    public boolean containsNode (IPathNode node) {
        return getNodes().contains(node);
    }

    @Override
    public void connectNodes (IPathNode first, IPathNode second) {
        addIfDoesNotContain(first, second.getNeighbors());
        addIfDoesNotContain(second, first.getNeighbors());
    }

    private void addIfDoesNotContain (IPathNode node, List<IPathNode> list) {
        if (!list.contains(node)) {
            list.add(node);
        }
    }

    @Override
    public IPathNode getClosestNode (Coordinate loc) {
        return getNodes()
                .stream()
                .reduce((x, y)
                         -> Coordinate.distance(x.getLocation(), loc) >=
                         Coordinate.distance(y.getLocation(), loc)
                         ? y : x)
                .orElse(null);
        
        
//        double closestDist = Double.MAX_VALUE;
//        IPathNode closest = null;
//        for (IPathNode node : getNodes()) {
//            if (Coordinate.distance(node.getLocation(), loc) < closestDist) {
//                closest = node;
//                closestDist = Coordinate.distance(node.getLocation(), loc);
//            }
//        }
//        return closest;
    }

    @Override
    public void addAndConnectNode (IPathNode toAdd) {
        connectNodes(toAdd, getClosestNode(toAdd.getLocation()));
        addNode(toAdd);
        
    }

    @Override
    public IPathNode[][] getPlacedNodes () {
        return myPlacedNodes;
    }

    @Override
    public void setPlacedNodes (IPathNode[][] toStore) {
        myPlacedNodes = toStore;
        
    }

    @Override
    public IPathNode addIfCantGetFor (Coordinate pos) {
        if(getNodes().contains(new PathNode(pos)))
        {
            return getNodes().stream()
                    .filter(node -> node.getLocation().equals(pos))
                    .reduce((a,b) -> a)
                    .orElse(null);
        }else{
            IPathNode toReturn = new PathNode(pos);
            addNode(toReturn);
            return toReturn;
        }
        
    }

}

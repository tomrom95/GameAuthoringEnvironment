package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import util.Coordinate;


public class NodeGraph implements INodeGraph {

    private List<IPathNode> myNodes;

    public NodeGraph () {
        myNodes = new ArrayList<>();
    }

    @Override
    public void addNode (IPathNode node) {
        //if(getAllCoordinates(getMyNodes()).contains(o))

    }

    @Override
    public void removeNode (IPathNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<IPathNode> getNodesNear (Coordinate loc, double maxDistance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<IPathNode> getGraph () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void applyToNodes (Consumer<IPathNode> toApply) {
        // TODO Auto-generated method stub

    }

    private List<IPathNode> getMyNodes () {
        return myNodes;
    }

    private List<Coordinate> getAllCoordinates (List<IPathNode> list) {
        return list
                .stream()
                .map(node -> node.getLocation())
                .collect(Collectors.toList());
    }

}

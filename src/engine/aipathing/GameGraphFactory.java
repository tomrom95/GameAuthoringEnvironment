package engine.aipathing;

import engine.IGame;
import util.BitMap;
import util.Coordinate;
import java.util.List;


public class GameGraphFactory implements INodeGraphFactory {

    private static final int NODE_GAP = 10;
    private BitMap myObstructionMap;
    private double myMinGap;

    GameGraphFactory (BitMap obstructionMap) {
        myObstructionMap = obstructionMap;
        myMinGap = Double.MAX_VALUE;
    }

    @Override
    public INodeGraph getConstructedGraph () {
        List<List<Coordinate>> edges = findAllEdges(getObstructionMap());
        return fillNodeGraph(getObstructionMap(), NODE_GAP, edges);

    }

    private NodeGraph fillNodeGraph (BitMap obstructionMap, int gap, List<List<Coordinate>> edges) {
        // TODO fill in method
        // tracking the nodes that we put in using an array
        int widthNodes = obstructionMap.getWidth() / gap;
        int heightNodes = obstructionMap.getHeight() / gap;
        PathNode[][] placedNodes = new PathNode[widthNodes][heightNodes];

        // fill map with nodes at gapped interval
        // place nodes half-way between different edges
        // connect nodes by doing distance search
        // distance gridDistance * sqrt(2) radius circle
        // Fill a new INodeGraph with nodes except where obstructed at minGap resolution
        // connect the nodes to their cardinal neighbors
        // maybe use a data structure to store this
        NodeGraph toReturn = new NodeGraph();
        return null;

    }

    private List<List<Coordinate>> findAllEdges (BitMap obstructionMap) {
        // TODO fill in method
        return null;
    }

    private BitMap getObstructionMap () {
        return myObstructionMap;
    }

    public double getMinGap () {
        return myMinGap;
    }

    private void setMinGap (double gap) {
        myMinGap = gap;
    }

}

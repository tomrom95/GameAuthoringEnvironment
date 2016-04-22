package engine.aipathing;

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

    private INodeGraph fillNodeGraph (BitMap obstructionMap,
                                      int gap,
                                      List<List<Coordinate>> edges) {
        // TODO fill in method
        // tracking the nodes that we put in using an array
        INodeGraph toReturn = new NodeGraph();
        int numHorizontalNodes = obstructionMap.getWidth() / gap;
        int numHeightNodes = obstructionMap.getHeight() / gap;
        IPathNode[][] placedNodes = new PathNode[numHorizontalNodes][numHeightNodes];
        // place the standard grid
        for (int i = 0; i < obstructionMap.getWidth(); i += gap) {
            for (int j = 0; j < obstructionMap.getHeight(); j += gap) {
                if (obstructionMap.valueOf(i, j)) {
                    IPathNode toAdd = new PathNode(new Coordinate(i, j));
                    toReturn.addNode(toAdd);
                    placedNodes[i][j] = toAdd;
                }
            }
        }
        connectUnobstructedNodes(placedNodes);
        List<IPathNode> traversableGapNodes = addEdgeNodes(toReturn, edges, obstructionMap);
        connectFloatingNodes(traversableGapNodes, toReturn, gap, placedNodes);
        return toReturn;

    }

    /**
     * 
     * @param nodes to add
     * @param graph
     * @param graphInterval the sample distance of the placed node
     * @param placedNodes cache of placed nodes to avoid having to check every node in graph
     */
    private void connectFloatingNodes (List<IPathNode> nodes,
                                       INodeGraph graph,
                                       int graphInterval,
                                       IPathNode[][] placedNodes,
                                       BitMap obstructionMap) {
        // connect nodes by doing distance search
        // distance NODE_GAP * sqrt(2) radius circle
        // TODO
    }

    private List<IPathNode> addEdgeNodes (INodeGraph graph,
                                          List<List<Coordinate>> edges,
                                          BitMap obstructionMap) {

        // fill map with nodes at gapped interval
        // place nodes half-way between different edge points if distance is <= NODE_GAP
        // TODO
        return null;
    }

    private void connectUnobstructedNodes (IPathNode[][] nodes) {
        // will connect cardinal and diagonal, for diagonal will simply check
        // one on each side of the line as well, so that corner blocks will stop
        // will check straight lines between nodes to see if there
        // are any obstructed pixels
        // TODO
    }

    /**
     * Will connect partially connected nodes
     * 
     * @param first Node to check
     * @param second Node to check
     * @return True if at least one node holds reference of the other, false if not
     */
    boolean ifConnected (IPathNode first, IPathNode second) {
        boolean connected = false;
        if (first.getNeighbors().contains(second) || second.getNeighbors().contains(first)) {
            connected = true;
            makeNeighborsIfNotAlready(first, second);
        }
        return connected;
    }

    void makeNeighborsIfNotAlready (IPathNode first, IPathNode second) {
        if (!first.getNeighbors().contains(second)) {
            first.getNeighbors().add(second);
        }
        if (!second.getNeighbors().contains(first)) {
            second.getNeighbors().add(first);
        }
    }

    /**
     * Will perform obstruction checks by checking a series of coordinates within the map
     * to see if they are all false
     * @param first Node in the pair to connect
     * @param second Node in the pair to connect
     * @param obstructionMap
     */
    private void connectIfNotObstructed (IPathNode first, IPathNode second, BitMap obstructionMap) {
        //TODO
    }
    
    private boolean checkIfLineObstructed(List<Coordinate> line){
        //TODO
        return false;
    }
    
    

    private List<List<Coordinate>> findAllEdges (BitMap obstructionMap) {
        BitMap destructiveCopy = new BitMap(obstructionMap);

        // TODO fill in method
        return null;
    }

    private List<Coordinate> recursiveEdgeHelper (BitMap obstructionMap, List<Coordinate> inEdge) {
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
